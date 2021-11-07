package com.example.interview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.interview.databinding.ActivityOrderBinding;

public class order extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        Log.e("now",intent.getExtras().getString("restuarantName"));
        Log.e("now",intent.getExtras().getString("restuarantadders"))   ;
        super.onCreate(savedInstanceState);

    }

}