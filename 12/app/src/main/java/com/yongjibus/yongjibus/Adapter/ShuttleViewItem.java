package com.yongjibus.yongjibus.Adapter;

public class ShuttleViewItem {
    private String stNumber;
    private String stName;
    private String stStart;
    private String stArrive;

    public ShuttleViewItem(String stNumber,String stName, String stStart, String stArrive){
        this.stNumber=stNumber;
        this.stName=stName;
        this.stStart=stStart;
        this.stArrive=stArrive;
    }
    public String getStArrive() {
        return stArrive;
    }
    public String getStName() {
        return stName;
    }
    public String getStNumber() {
        return stNumber;
    }
    public String getStStart() {
        return stStart;
    }
    public void setStArrive(String stArrive) {
        this.stArrive = stArrive;
    }
    public void setStName(String stName) {
        this.stName = stName;
    }
    public void setStNumber(String stNumber) {
        this.stNumber = stNumber;
    }
    public void setStStart(String stStart) {
        this.stStart = stStart;
    }
}
