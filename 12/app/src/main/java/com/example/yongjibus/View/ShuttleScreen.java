package com.example.yongjibus.View;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yongjibus.Adapter.BusBoxVIewItem;
import com.example.yongjibus.Adapter.BusBoxViewItemAdapter;
import com.example.yongjibus.Adapter.ShuttleViewItem;
import com.example.yongjibus.Adapter.ShuttleViewItemAdapter;
import com.example.yongjibus.MainActivity;
import com.example.yongjibus.Model.BusNumber;
import com.example.yongjibus.ModelView.BusBoxViewModel;
import com.example.yongjibus.ModelView.ShuttleTimeViewModel;
import com.example.yongjibus.Progress.ProgressDialog;
import com.example.yongjibus.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class ShuttleScreen extends Fragment {
   //진입로 버스 관련 변수
    private LinearLayoutManager busLinearLayoutManager;
    private RecyclerView busRecyclerView;
    private ArrayList<BusBoxVIewItem> busDataList;
    private BusBoxViewItemAdapter busAdapter;
    private SwipeRefreshLayout swipe;
    //명지대역 셔틀 관련 변수
    private LinearLayoutManager shuttleLinearLayoutManager;
    private RecyclerView shuttleRecyclerView;
    private ArrayList<ShuttleViewItem> shuttleDataList;
    private ShuttleViewItemAdapter shuttleAdapter;
    private ProgressDialog customProgressDialog;
    private ImageView refresh;
    private ImageView setting;
    private Fragment frag=this;
    private Setting setting_view;
    private int loadedBusCount=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onResume() {
        super.onResume();

        BusBoxViewModel bw=new BusBoxViewModel(this.getActivity());

        customProgressDialog = new ProgressDialog(this.getContext());
        customProgressDialog.setCancelable(false); // 로딩창 주변 클릭 시 종료 막기
        //로딩창을 투명하게 하는 코드
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //getWindow (): 현재 액티비티의 Window 객체를 가져와서 Window 객체를 통해 뷰들의 위치 크기, 색상 조절
        //Window는 View 의 상위 개념으로, 뷰들을(버튼, 텍스트뷰, 이미지뷰) 감쌓고 있는 컨테이너 역할을 함
        customProgressDialog.show();

        //버스 어댑터 생성
        busDataList=new ArrayList<>();
        busAdapter=new BusBoxViewItemAdapter(busDataList);


        loadBus(bw);



        busRecyclerView.addItemDecoration(new ShuttleScreen.RecyclerViewDecoration(20));
        busLinearLayoutManager = new LinearLayoutManager(getActivity());
        busLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        busRecyclerView.setLayoutManager(busLinearLayoutManager);
        busRecyclerView.setAdapter(busAdapter);
        busRecyclerView.setHasFixedSize(false);

    }

    @Override
    public void onStart(){
        super.onStart();



//        BusBoxViewModel bw=new BusBoxViewModel(this.getActivity());
//
//        customProgressDialog = new ProgressDialog(this.getContext());
//        customProgressDialog.setCancelable(false); // 로딩창 주변 클릭 시 종료 막기
//        //로딩창을 투명하게 하는 코드
//        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        //getWindow (): 현재 액티비티의 Window 객체를 가져와서 Window 객체를 통해 뷰들의 위치 크기, 색상 조절
//        //Window는 View 의 상위 개념으로, 뷰들을(버튼, 텍스트뷰, 이미지뷰) 감쌓고 있는 컨테이너 역할을 함
//        customProgressDialog.show();
//
//        //버스 어댑터 생성
//        busDataList=new ArrayList<>();
//        busAdapter=new BusBoxViewItemAdapter(busDataList);
////        for(int i=0;i<5;i++){
////            BusBoxVIewItem item=new BusBoxVIewItem("5001번",i+"분 남음",R.drawable.bus );
////            busDataList.add(0,item);
////            busAdapter.notifyDataSetChanged();
////        }
//
//       loadBus(bw);
//
//
//
//        busRecyclerView.addItemDecoration(new ShuttleScreen.RecyclerViewDecoration(20));
//        busLinearLayoutManager = new LinearLayoutManager(getActivity());
//        busLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        busRecyclerView.setLayoutManager(busLinearLayoutManager);
//        busRecyclerView.setAdapter(busAdapter);
//        busRecyclerView.setHasFixedSize(false);
        //명지대역 셔틀 어댑터 생성
        ShuttleTimeViewModel stModel=new ShuttleTimeViewModel();
        shuttleDataList=stModel.getJsonString(this.getContext());
        Collections.reverse(shuttleDataList);
        shuttleAdapter=new ShuttleViewItemAdapter(shuttleDataList);
        shuttleAdapter.notifyDataSetChanged();

        shuttleRecyclerView.addItemDecoration(new ShuttleScreen.RecyclerViewDecorationForHeight(20));
        shuttleLinearLayoutManager = new LinearLayoutManager(getActivity());
        shuttleLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shuttleRecyclerView.setLayoutManager(shuttleLinearLayoutManager);
        shuttleRecyclerView.setAdapter(shuttleAdapter);
        shuttleRecyclerView.setHasFixedSize(false);




    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_shuttle_screen,container,false);
        //광고




        //진입로 빨버 관련
        busRecyclerView=(RecyclerView) view.findViewById(R.id.bus_recyclerView);
        busRecyclerView.setHasFixedSize(true);


        // 명지대역 셔틀 관련
        shuttleRecyclerView=(RecyclerView) view.findViewById(R.id.shuttle_recyclerView);
        shuttleRecyclerView.setHasFixedSize(true);

        refresh=(ImageView) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshBusData();
            }
        });

        setting=(ImageView)view.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_view = new Setting(getContext());
                setting_view.show();

            }
        });

        return view;


    }
    private void loadBus(BusBoxViewModel bw) {
        loadedBusCount=0;

        // 첫 번째 버스 데이터 로딩
        bw.load(BusNumber.one, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                // 데이터 로딩 완료 후 처리
                handleBusLoadingResult(arrivalTime, "5001-1");
            }
        });

        // 두 번째 버스 데이터 로딩
        bw.load(BusNumber.three, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                // 데이터 로딩 완료 후 처리
                handleBusLoadingResult(arrivalTime, "5003B");
            }
        });

        // 세 번째 버스 데이터 로딩
        bw.load(BusNumber.zero, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                // 데이터 로딩 완료 후 처리
                handleBusLoadingResult(arrivalTime, "5000B");
            }
        });
    }

        // 각 버스 데이터 로딩이 완료될 때 호출되는 메서드
    private void handleBusLoadingResult(String arrivalTime, String busNumber) {
        BusBoxVIewItem item;
        String arriveTime = arrivalTime;

        if (Integer.parseInt(arriveTime) >= 60) {
            int hour = Integer.parseInt(arriveTime) / 60;
            int min = Integer.parseInt(arriveTime) % 60;
            arriveTime = hour + "시간 " + min;
        }
        if (Integer.valueOf(arrivalTime) != -1) {
            item = new BusBoxVIewItem(busNumber, arriveTime + "분 남음", R.drawable.bus);
        } else {
            item = new BusBoxVIewItem(busNumber, "정보 없음", R.drawable.bus);
        }

        busDataList.add(item);
        busAdapter.notifyDataSetChanged();

        // 한 버스의 데이터 로딩이 완료되면 카운터 증가
        loadedBusCount++;

        // 모든 버스의 데이터 로딩이 완료되면 ProgressDialog 종료
        if (loadedBusCount == 3) {
            customProgressDialog.dismiss();
        }
    }


    private void refreshBusData() {
        // 기존 데이터를 지우고, 프로그레스 다이얼로그를 보여줍니다.
        busDataList.clear();
        busAdapter.notifyDataSetChanged();
        customProgressDialog.show();

        BusBoxViewModel bw = new BusBoxViewModel(getActivity());
        loadBus(bw);
        // 데이터 로딩이 완료되면 프로그레스 다이얼로그를 숨깁니다.

    }
    public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

        private final int divWidth;

        public RecyclerViewDecoration(int divWidth)
        {
            this.divWidth = divWidth;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.right = divWidth;
        }
    }
    public class RecyclerViewDecorationForHeight extends RecyclerView.ItemDecoration {

        private final int divHeight;

        public RecyclerViewDecorationForHeight(int divHeight)
        {
            this.divHeight = divHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = divHeight;
        }
    }
}