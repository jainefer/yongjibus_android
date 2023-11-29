package com.example.yongjibus.View;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.yongjibus.Adapter.GihuengStationViewItem;
import com.example.yongjibus.Adapter.GihuengStationViewItemAdapter;
import com.example.yongjibus.ModelView.GihuengTimeViewModel;
import com.example.yongjibus.R;

import java.util.ArrayList;
import java.util.Collections;


public class GihuengStationScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private LinearLayoutManager gsLinearLayoutManager;
    private RecyclerView gsRecyclerView;
    private ArrayList<GihuengStationViewItem> gsDataList;
    private GihuengStationViewItemAdapter gsAdapter;

    private Setting setting_view;
    private ImageView setting;
    @Override
    public void onStart(){
        super.onStart();
        //버스 어댑터 생성
        GihuengTimeViewModel gsModel=new GihuengTimeViewModel();
        gsDataList=new ArrayList<>();
        gsDataList=gsModel.getJsonString(this.getContext());
        Collections.reverse(gsDataList);
        gsAdapter=new GihuengStationViewItemAdapter(gsDataList);
        gsAdapter.notifyDataSetChanged();
        gsRecyclerView.addItemDecoration(new GihuengStationScreen.RecyclerViewDecorationForHeight(20));
        gsLinearLayoutManager = new LinearLayoutManager(getActivity());
        gsLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gsRecyclerView.setLayoutManager(gsLinearLayoutManager);
        gsRecyclerView.setAdapter(gsAdapter);
        gsRecyclerView.setHasFixedSize(false);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gihueng_station_screen,container,false);
        //기흥역
        gsRecyclerView=(RecyclerView) view.findViewById(R.id.gihueng_recyclerView);
        gsRecyclerView.setHasFixedSize(true);
        // Inflate the layout for this fragment
        //설정창
//
        setting=(ImageView)view.findViewById(R.id.gh_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_view = new Setting(getContext());
                setting_view.show();

            }
        });




        return view;
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