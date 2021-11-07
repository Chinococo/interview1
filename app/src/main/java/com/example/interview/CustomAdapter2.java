package com.example.interview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter2 extends ArrayAdapter {
    List<HashMap<String,String>> arrayList;
    Context context;
    private int resourceId;
    ImageView plus,minus;

    public CustomAdapter2(Context context1, int textViewResourceId, List<HashMap<String,String>> objects){
        super(context1,textViewResourceId,objects);
        arrayList = objects;
        resourceId = textViewResourceId;
        context = context1;

    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        HashMap<String,String> data= arrayList.get(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //ImageView Image = (ImageView)view.findViewById(R.id.fruit_image);
        TextView name=(TextView) view.findViewById(R.id.ItemName);
        TextView price=(TextView) view.findViewById(R.id.ItemPrice);
        TextView now_count;
        plus = view.findViewById(R.id.imageplus);
        minus = view.findViewById(R.id.imageminus);
        now_count = view.findViewById(R.id.count);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now_count.setText(""+(Integer.parseInt(now_count.getText().toString())+1));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(now_count.getText().toString())>0)
                now_count.setText(""+(Integer.parseInt(now_count.getText().toString())-1));
            }
        });
        //Image.setImageResource(fruit.getImageId());
        System.out.println(data.toString());
        name.setText("商品:"+data.get("itemName"));
        price.setText("價格:"+data.get("itemPrice"));
        return view;
    }

}
