package com.example.yongjibus.View;

import static com.example.yongjibus.Model.BusNumber.zero;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yongjibus.Adapter.BusBoxViewItemAdapter;
import com.example.yongjibus.Model.BusNumber;
import com.example.yongjibus.ModelView.BusBoxViewModel;
import com.example.yongjibus.R;

import java.util.ArrayList;


public class BusBoxView extends Fragment {
    private String title;
    private BusNumber busNumber;
    private BusBoxViewModel busBoxVIewModel;
    private HorizontalScrollView scrollView;

    private RecyclerView recyclerView;
    private BusBoxViewItemAdapter busBoxViewItemAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        this.busNumber=busNumber;

        switch(busNumber){
            case one:
                this.title = "5001-1";
                break;
            case three:
                this.title = "5003B";
                break;
            case zero:
                this.title = "5000B";
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_bus_box_view,container,false);

        busBoxVIewModel=new BusBoxViewModel();
        busBoxVIewModel.load(busNumber);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        recyclerView.setAdapter(busBoxViewItemAdapter);



        this.busNumber=busNumber;

        switch(busNumber){
            case one:
                this.title = "5001-1";
                break;
            case three:
                this.title = "5003B";
                break;
            case zero:
                this.title = "5000B";
                break;
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_box_view, container, false);
    }
}