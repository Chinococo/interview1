package com.example.interview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class logout extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView now;
    Button button;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public logout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_logout2, container, false);
        button =  view.findViewById(R.id.btn_logout);
        now = Objects.requireNonNull(getActivity()).findViewById(R.id.nowaccountname);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test","test");
                if(!now.getText().toString().equals("name:none"))
                {
                    Toast.makeText(getContext(),"登出成功",Toast.LENGTH_LONG).show();
                    now.setText("name:none");
                }else
                {
                    Toast.makeText(getContext(),"尚未登入",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}