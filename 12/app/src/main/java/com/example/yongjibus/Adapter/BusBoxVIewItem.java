package com.example.yongjibus.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yongjibus.R;

public class BusBoxVIewItem {
    private int iconDrawbalbe;
    private String contentStr;
    private String titleStr;
    public void setTitle(String title){
        titleStr=title;
    }
    public void setIcon(int icon){
        iconDrawbalbe = icon;
    }
    public void setContent(String content){
        contentStr=content;
    }
    public  int getIcon() {
        return this.iconDrawbalbe;
    }
    public String getContent(){
        return this.contentStr;
    }
    public  String getTitle(){
        return this.titleStr;
    }

}