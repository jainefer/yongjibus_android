package com.example.yongjibus.Adapter;

public class GihuengStationViewItem {
    private String gs_number;
    private String gs_start;
    private String gs_stopover;
    private String gs_arrive;
    private String gs_unit;

    public GihuengStationViewItem(String gs_number, String gs_start, String gs_stopover, String gs_arrive, String gs_unit) {

        this.gs_number = gs_number;
        this.gs_start = gs_start;
        this.gs_stopover = gs_stopover;
        this.gs_arrive = gs_arrive;
        this.gs_unit = gs_unit;
    }

    public String getGs_arrive() {
        return gs_arrive;
    }

    public String getGs_number() {
        return gs_number;
    }

    public String getGs_start() {
        return gs_start;
    }

    public String getGs_stopover() {
        return gs_stopover;
    }

    public String getGs_unit() {
        return gs_unit;
    }

    public void setGs_arrive(String gs_arrive) {
        this.gs_arrive = gs_arrive;
    }

    public void setGs_number(String gs_number) {
        this.gs_number = gs_number;
    }

    public void setGs_start(String gs_start) {
        this.gs_start = gs_start;
    }

    public void setGs_stopover(String gs_stopover) {
        this.gs_stopover = gs_stopover;
    }

    public void setGs_unit(String gs_unit) {
        this.gs_unit = gs_unit;
    }
}
