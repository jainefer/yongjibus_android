package com.example.yongjibus.View;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart(){
        super.onStart();

        BusBoxViewModel bw=new BusBoxViewModel(this.getActivity());
        //버스 어댑터 생성
        busDataList=new ArrayList<>();
        busAdapter=new BusBoxViewItemAdapter(busDataList);
//        for(int i=0;i<5;i++){
//            BusBoxVIewItem item=new BusBoxVIewItem("5001번",i+"분 남음",R.drawable.bus );
//            busDataList.add(0,item);
//            busAdapter.notifyDataSetChanged();
//        }
        bw.load(BusNumber.one, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                String arriveTime1=arrivalTime;
                if(Integer.parseInt(arriveTime1)>=60){
                    int hour= Integer.parseInt(arriveTime1)/60;
                    int min= Integer.parseInt(arriveTime1)%60;
                    arriveTime1=hour+"시간 "+min;
                }
                BusBoxVIewItem item=new BusBoxVIewItem("5001-1",arriveTime1+"분 남음",R.drawable.bus );
                busDataList.add(item);
                busAdapter.notifyDataSetChanged();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bw.load(BusNumber.three, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                String arriveTime2=arrivalTime;
                if(Integer.parseInt(arriveTime2)>=60){
                    int hour= Integer.parseInt(arriveTime2)/60;
                    int min= Integer.parseInt(arriveTime2)%60;
                    arriveTime2=hour+"시간 "+min;
                }
                BusBoxVIewItem item=new BusBoxVIewItem("5003B",arriveTime2+"분 남음",R.drawable.bus );
                busDataList.add(item);
                busAdapter.notifyDataSetChanged();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bw.load(BusNumber.zero, new BusBoxViewModel.OnBusArrivalListener() {
            @Override
            public void onBusArrival(String arrivalTime) {
                String arriveTime3=arrivalTime;
                if(Integer.parseInt(arriveTime3)>=60){
                    int hour= Integer.parseInt(arriveTime3)/60;
                    int min= Integer.parseInt(arriveTime3)%60;
                    arriveTime3=hour+"시간 "+min;
                }
                BusBoxVIewItem item=new BusBoxVIewItem("5000B",arriveTime3+"분 남음",R.drawable.bus );
                busDataList.add(item);
                busAdapter.notifyDataSetChanged();
            }
        });


        busRecyclerView.addItemDecoration(new ShuttleScreen.RecyclerViewDecoration(20));
        busLinearLayoutManager = new LinearLayoutManager(getActivity());
        busLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        busRecyclerView.setLayoutManager(busLinearLayoutManager);
        busRecyclerView.setAdapter(busAdapter);
        busRecyclerView.setHasFixedSize(false);
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
        //진입로 빨버 관련
        busRecyclerView=(RecyclerView) view.findViewById(R.id.bus_recyclerView);
        busRecyclerView.setHasFixedSize(true);


        // 명지대역 셔틀 관련
        shuttleRecyclerView=(RecyclerView) view.findViewById(R.id.shuttle_recyclerView);
        shuttleRecyclerView.setHasFixedSize(true);

        return view;


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