package com.example.interview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends Fragment {
    EditText account, password;
    Button submit;
    TextView resign;

    public login() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getView().findViewById(R.id.btn_login);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        account = view.findViewById(R.id.EditTextlogin);
        password = view.findViewById(R.id.EdittextPassword);
        submit = view.findViewById(R.id.btn_login);
        TextView textView = getActivity().findViewById(R.id.nowaccountname);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("account");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!account.getText().toString().equals("")&&!password.getText().toString().equals(""))
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(account.getText().toString()).getValue() != null)
                        {
                            Log.e("e",snapshot.child(account.getText().toString()).getValue().toString());
                            if (snapshot.child(account.getText().toString()).child("passWord").getValue().toString().equals(password.getText().toString())) {
                                Toast.makeText(getContext(),"login in is scuccesful",Toast.LENGTH_LONG).show();
                                textView.setText(account.getText().toString());
                            } else
                                Toast.makeText(getContext(),"login in not scuccesful",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getContext(),"註冊成功",Toast.LENGTH_LONG).show();
                            db.child(account.getText().toString()).setValue(new account(account.getText().toString(), password.getText().toString()));
                            textView.setText(account.getText().toString());
                        }
                        account.setText("");
                        password.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                else
                    Toast.makeText(getContext(),"資料不完整",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}

class account {
    String account;
    String password;

    account(String a, String b) {
        account = a;
        password = b;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }
}