package com.example.yongjibus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ShuttleScreen shuttle;
    GihuengStationScreen gihuengStation;
    ProgressDialog customProgressDialog;
    private AdView mAdview; //애드뷰 변수 선언
    private boolean state=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        shuttle=new ShuttleScreen();
        gihuengStation=new GihuengStationScreen();

        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new ShuttleScreen()).commit();
        BottomNavigationView bottomNavigation= findViewById(R.id.bottom_navigation);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        mAdview = findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Shuttle:
                        if(!state) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new ShuttleScreen()).commit();
                            state=true;
                        }
                        return true;

                    case R.id.GihuengStation:
                        if(state) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new GihuengStationScreen()).commit();
                            state=false;
                        }
                        return true;
                }
                return false;
            }
        });


    }


}