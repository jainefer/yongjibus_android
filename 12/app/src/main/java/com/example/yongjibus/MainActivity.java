package com.example.yongjibus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.yongjibus.Adapter.BusBoxVIewItem;
import com.example.yongjibus.Adapter.BusBoxViewItemAdapter;
import com.example.yongjibus.ModelView.BusBoxViewModel;
import com.example.yongjibus.R;
import com.example.yongjibus.View.GihuengStationScreen;
import com.example.yongjibus.View.ShuttleScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BusBoxViewModel b=new BusBoxViewModel();
    ShuttleScreen shuttle;
    GihuengStationScreen gihuengStation;



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
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container,new ShuttleScreen()).commit();

                        return true;

                    case R.id.GihuengStation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container,new GihuengStationScreen()).commit();
                        return true;
                }
                return false;
            }
        });


    }


}