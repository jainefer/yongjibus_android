package com.example.yongjibus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.yongjibus.Adapter.BusBoxVIewItem;
import com.example.yongjibus.Adapter.BusBoxViewItemAdapter;
import com.example.yongjibus.ModelView.BusBoxViewModel;
import com.example.yongjibus.Progress.ProgressDialog;
import com.example.yongjibus.R;
import com.example.yongjibus.View.GihuengStationScreen;
import com.example.yongjibus.View.ShuttleScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ShuttleScreen shuttle;
    GihuengStationScreen gihuengStation;
    ProgressDialog customProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuttle=new ShuttleScreen();
        gihuengStation=new GihuengStationScreen();

        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new ShuttleScreen()).commit();
        BottomNavigationView bottomNavigation= findViewById(R.id.bottom_navigation);


        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Shuttle:
//                        customProgressDialog.show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container,new ShuttleScreen()).commit();
//                        customProgressDialog.dismiss();
                        return true;

                    case R.id.GihuengStation:
//                        customProgressDialog.show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container,new GihuengStationScreen()).commit();
//                        customProgressDialog.dismiss();
                        return true;
                }
                return false;
            }
        });


    }


}