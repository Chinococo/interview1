package com.example.interview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.interview.databinding.ActivityOrderBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class order extends AppCompatActivity {
    public ArrayList<HashMap<String,String>> all_order = new ArrayList<>();
    ListView listview;
    int sum;
    Button button;
    ArrayList<HashMap<String,String>> submitdata;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ArrayList<HashMap<String,String>> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        Log.e("now",intent.getExtras().getString("restuarantName"));
        listview = findViewById(R.id.goodListview);
        db.child("menu").child(intent.getExtras().getString("restuarantName")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                {
                    data = (ArrayList<HashMap<String, String>>) snapshot.getValue();
                    CustomAdapter2 adapter2 = new CustomAdapter2(order.this,R.layout.menu_item,data);
                    listview.setAdapter(adapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button = findViewById(R.id.sumup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum=0;
                String data="";
                int itemsCount = listview.getChildCount();
                submitdata = new ArrayList<HashMap<String,String>>();
                for (int i = 0; i < itemsCount; i++) {
                    View view = listview.getChildAt(i);
                    String good = ((TextView) view.findViewById(R.id.ItemName)).getText().toString();
                    String price = ((TextView) view.findViewById(R.id.ItemPrice)).getText().toString();
                    String count = ((TextView) view.findViewById(R.id.count)).getText().toString();
                    HashMap<String,String> temp = new HashMap<>();
                    temp.put("GoodName",good.substring(3));
                    temp.put("price",price.substring(3));
                    temp.put("count",count);
                    if(Integer.parseInt(count)!=0) {
                        submitdata.add(temp);
                        sum+=Integer.parseInt(price.substring(3)) * Integer.parseInt(count);
                        data += good + " " + price + " * " + count + " = " + Integer.parseInt(price.substring(3)) * Integer.parseInt(count) + "\n";
                    }
                }
                data+="總計:"+sum;
                new AlertDialog.Builder(order.this)
                        .setTitle("是否送出")
                        .setMessage(data)
                        .setCancelable(false)
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton("送出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        reference.child("Task").child(intent.getExtras().getString("restuarantName")).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ArrayList<ArrayList<HashMap<String,String>>> t = new ArrayList<>();
                                if(snapshot.getValue()!=null)
                                {
                                    t = (ArrayList<ArrayList<HashMap<String, String>>>) snapshot.getValue();
                                }
                                t.add(submitdata);
                                reference.child("Task").child(intent.getExtras().getString("restuarantName")).setValue(t);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }).show();
                Log.e("sumup",data);
            }
        });

        super.onCreate(savedInstanceState);

    }

}