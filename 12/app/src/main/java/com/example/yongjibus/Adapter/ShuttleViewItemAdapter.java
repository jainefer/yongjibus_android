package com.example.yongjibus.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yongjibus.R;

import java.util.ArrayList;

public class ShuttleViewItemAdapter extends RecyclerView.Adapter<ShuttleViewItemAdapter.ViewHolder> {
    private ArrayList<ShuttleViewItem> shuttleData;
    public ShuttleViewItemAdapter(ArrayList<ShuttleViewItem> shuttleData){
        this.shuttleData=shuttleData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shuttle_view_item,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ShuttleViewItemAdapter.ViewHolder holder, int position){
        holder.stNumber.setText(shuttleData.get(position).getStNumber());
        holder.stName.setText(shuttleData.get(position).getStName());
        holder.stStart.setText(shuttleData.get(position).getStStart());
        holder.stArrive.setText(shuttleData.get(position).getStArrive());
    }
    @Override
    public int getItemCount(){
        return shuttleData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView stName;
        private TextView stNumber;
        private TextView stStart;
        private TextView stArrive;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            stName=(TextView) itemView.findViewById(R.id.shuttle_name);
            stNumber=(TextView) itemView.findViewById(R.id.shuttle_number);
            stStart=(TextView) itemView.findViewById(R.id.shuttle_start);
            stArrive=(TextView) itemView.findViewById(R.id.shuttle_arrive);
        }
    }
}
