package com.example.interview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter2 extends ArrayAdapter {
    List<HashMap<String,String>> arrayList;
    Context context;
    private int resourceId;

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
        //Image.setImageResource(fruit.getImageId());
        System.out.println(data.toString());
        name.setText("商品:"+data.get("itemName"));
        price.setText("價格:"+data.get("itemPrice"));
        return view;
    }
}
