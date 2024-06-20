package com.yongjibus.yongjibus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.yongjibus.yongjibus.Progress.ProgressDialog;
import com.example.yongjibus.R;
import com.yongjibus.yongjibus.View.GihuengStationScreen;
import com.yongjibus.yongjibus.View.SettingScreen;
import com.yongjibus.yongjibus.View.ShuttleScreen;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    ShuttleScreen shuttle;
    GihuengStationScreen gihuengStation;

    SettingScreen settingScreen;
    ProgressDialog customProgressDialog;
    private AdView mAdview; //애드뷰 변수 선언
    private int state=0;
    private BottomNavigationView bottomNavigation;
    public void onFragmentChange(int index){
        System.out.println("테스트성공");
        if(index == 1){
            System.out.println("테스트성공2");
            bottomNavigation= findViewById(R.id.bottom_navigation);
            bottomNavigation.setSelectedItemId(R.id.Setting);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        shuttle=new ShuttleScreen();
        gihuengStation=new GihuengStationScreen();
        settingScreen =new SettingScreen();
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new ShuttleScreen()).commit();
        bottomNavigation= findViewById(R.id.bottom_navigation);



//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
//
//            }
//        });

//        mAdview = findViewById(R.id.adView); //배너광고 레이아웃 가져오기
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdview.loadAd(adRequest);
        bottomNavigation.setSelectedItemId(R.id.Shuttle);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Shuttle:
                        if(state!=0) {

                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new ShuttleScreen()).commit();
                            state=0;
                        }
                        return true;

                    case R.id.GihuengStation:
                        if(state!=1) {

                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new GihuengStationScreen()).commit();
                            state=1;
                        }
                        return true;
                    case R.id.Setting:
                        if(state!=2) {

                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container, new SettingScreen()).commit();
                            state=2;
                        }
                        return true;
                }
                return false;
            }
        });




    }


}