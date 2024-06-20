package com.yongjibus.yongjibus.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yongjibus.R;

import java.util.ArrayList;

public class BusBoxViewItemAdapter extends RecyclerView.Adapter<BusBoxViewItemAdapter.ViewHolder> {
   private ArrayList<BusBoxVIewItem> list;


    public BusBoxViewItemAdapter(ArrayList<BusBoxVIewItem> list){
        this.list=list;
    }



    @NonNull
    @Override
    public BusBoxViewItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bus_box_view_item,parent,false);
        ViewHolder viewHolder = new BusBoxViewItemAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusBoxViewItemAdapter.ViewHolder holder, int position){
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
        holder.icon.setImageResource(list.get(position).getIcon());

    }

    @Override
    public int getItemCount(){
        return (null !=list?list.size() :0);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;
        ImageView icon;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView) itemView.findViewById(R.id.time);
            icon=(ImageView) itemView.findViewById(R.id.icon);
        }
    }












}
