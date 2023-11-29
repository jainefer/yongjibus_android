package com.example.yongjibus.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yongjibus.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GihuengStationViewItemAdapter extends RecyclerView.Adapter<GihuengStationViewItemAdapter.ViewHolder> {
    private ArrayList<GihuengStationViewItem> gsData;
    public GihuengStationViewItemAdapter (ArrayList<GihuengStationViewItem> gsData){
        this.gsData=gsData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_giheung_station_view_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull GihuengStationViewItemAdapter.ViewHolder holder, int position) {

        holder.gsNumber.setText(gsData.get(position).getGs_number());
        holder.gsStart.setText(gsData.get(position).getGs_start());
        holder.gsStopOver.setText(gsData.get(position).getGs_stopover());
        holder.gsArrive.setText(gsData.get(position).getGs_arrive());
        holder.gsUnit.setText(gsData.get(position).getGs_unit());

    }


    @Override
    public int getItemCount() {
        return gsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView gsNumber;
        private TextView gsStart;
        private TextView gsStopOver;
        private TextView gsArrive;
        private TextView gsUnit;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            gsNumber=(TextView) itemView.findViewById(R.id.gihueng_number);
            gsStart=(TextView) itemView.findViewById(R.id.gihueng_start);
            gsStopOver=(TextView) itemView.findViewById(R.id.gihueng_stopover);
            gsArrive=(TextView) itemView.findViewById(R.id.gihueng_arrive);
            gsUnit=(TextView) itemView.findViewById(R.id.gihueng_unit);
        }

    }
}
