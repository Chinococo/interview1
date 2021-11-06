package com.example.interview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    List<Item> arrayList;
    Context context;
    private int resourceId;
    public CustomAdapter(Context context, int textViewResourceId, List<Item> objects){
        super(context,textViewResourceId,objects);
        arrayList = objects;
        resourceId = textViewResourceId;
        context = context;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Item data= arrayList.get(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //ImageView Image = (ImageView)view.findViewById(R.id.fruit_image);
        TextView name=(TextView) view.findViewById(R.id.itemresurantname);
        TextView adderss=(TextView) view.findViewById(R.id.itemresurantadders);
        //Image.setImageResource(fruit.getImageId());
        name.setText(data.getName());
        name.setText(data.getAdders());
        return view;
    }
}
