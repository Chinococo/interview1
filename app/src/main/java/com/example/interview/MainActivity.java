package com.example.interview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    ImageView imageView;
    DrawerLayout drawerLayout;
    SharedPreferences sb;
    Boolean signin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildNavgation();
        putrestuarnt();
        Readpreaccount();
    }

    void Readpreaccount() {
        sb = getSharedPreferences("account", MODE_WORLD_READABLE);
        String account = sb.getString("account", "-1");
        String email = sb.getString("email", "-1");
        signin = account.equals("-1") || email.equals("-1");


    }

    void putrestuarnt() {
        ArrayList<Item> data = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            data.add(new Item("item" + i, "adderss" + i));
        Log.e("TAG", data.toString());
        ListView listView = findViewById(R.id.resturant);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.resturant_item, data);
        listView.setAdapter(adapter);
    }

    void buildNavgation() {
        drawerLayout = findViewById(R.id.drawlayout);
        findViewById(R.id.menubtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navgationview);
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.fragmentlayout);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

}