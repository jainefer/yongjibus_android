package com.yongjibus.yongjibus.Adapter;

public class BusBoxVIewItem {
    private int iconDrawbalbe;
    private String contentStr;
    private String titleStr;
    public BusBoxVIewItem(String titleStr, String contentStr,int iconDrawbalbe){
        this.titleStr=titleStr;
        this.contentStr=contentStr;
        this.iconDrawbalbe=iconDrawbalbe;
    }


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