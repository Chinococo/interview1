package com.example.interview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
    ListView listview;
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



        super.onCreate(savedInstanceState);

    }

}