package com.example.yongjibus.Adapter;

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









    /*@Override
    public int getCount() {
        return busBoxVIewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return busBoxVIewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_bus_box_view_item,parent,false);
        }

        titleTextView=(TextView) convertView.findViewById(R.id.title);
        iconImageView=(ImageView) convertView.findViewById(R.id.icon);
        timeView=(TextView)convertView.findViewById(R.id.time);

        BusBoxVIewItem busBoxVIewItem= busBoxVIewItemList.get(position);
        titleTextView.setText(busBoxVIewItem.getTitle());
        iconImageView.setImageResource(busBoxVIewItem.getIcon());
        timeView.setText(busBoxVIewItem.getContent());

        return convertView;
    }
    public void addItem(String title,int icon, String content){
        BusBoxVIewItem item=new BusBoxVIewItem();

        item.setTitle(title);
        item.setIcon(icon);
        item.setContent(content);

        busBoxVIewItemList.add(item);
    }
        */


}
