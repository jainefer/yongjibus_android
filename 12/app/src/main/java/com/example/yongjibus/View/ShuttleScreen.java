package com.example.yongjibus.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.yongjibus.Adapter.BusBoxViewItemAdapter;
import com.example.yongjibus.Model.BusNumber;
import com.example.yongjibus.ModelView.BusBoxViewModel;
import com.example.yongjibus.R;


public class ShuttleScreen extends Fragment {
    private String title;
    private BusNumber busNumber;
    private BusBoxViewModel busBoxVIewModel;
    private HorizontalScrollView scrollView;

    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_bus_box_view,container,false);
        recyclerView=v.findViewById(R.id.recyclerView);
        busBoxVIewModel=new BusBoxViewModel();
        //busBoxVIewModel.load(busNumber);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        BusBoxViewItemAdapter busBoxViewItemAdapter=new BusBoxViewItemAdapter("50005","9"+"분남았습니다");
        recyclerView.setAdapter(busBoxViewItemAdapter);



        /*this.busNumber=busNumber;

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
        }*/

        return inflater.inflate(R.layout.fragment_shuttle_screen, container, false);
    }
}