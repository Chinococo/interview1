package com.example.interview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class create_account extends DialogFragment {
    EditText account, password;
    Button enter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String account_prev = "", password_prev = "";
    HashMap<String, String> worker, worker2 = new HashMap<>();

    @Override
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String temp = getArguments().getString("account");
        worker = (HashMap<String, String>) getArguments().getSerializable("worker");
        get(temp);
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.makeaccount, null);
        alertdialog.setView(view).setCancelable(false);
        account = view.findViewById(R.id.Edittedxt_createaccount_account);
        password = view.findViewById(R.id.Edittext_create_accountpassword);
        enter = view.findViewById(R.id.btn_createaccount_submit);
        /*
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().equals("")) {
                    nofition("??????????????????");
                } else {
                    if (password.getText().toString().equals("")) {
                        nofition("??????????????????");
                    } else {
                        if (account_prev.equals(account.getText().toString()) && password_prev.equals(password.getText().toString())) {
                            databaseReference.child("account").child(account_prev).removeValue();
                            databaseReference.child("account").child(worker.get("account")).setValue(worker);
                            databaseReference.child("id").child(worker.get("no")).setValue(worker.get("account"));
                            Log.d("test", worker.get("account") + "123");
                            nofition("succedful ??????\n??????????????????"+account_prev);
                            //Log.e("fin","fin");
                            Intent intent = new Intent(getContext(),MainActivity.class);
                            startActivity(intent);
                            getDialog().dismiss();
                        } else {
                            nofition("????????????");
                        }
                    }
                }
            }
        });

         */
        return alertdialog.create();
    }

    void nofition(String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_LONG).show();
    }

    void get(String tag) {

        databaseReference.child("account").child(tag).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                worker2 = (HashMap<String, String>) dataSnapshot.getValue();
                if (dataSnapshot.getValue() != null) {
                    account_prev = worker2.get("account");
                    password_prev = worker2.get("password");
                } else {
                    nofition("no get");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
