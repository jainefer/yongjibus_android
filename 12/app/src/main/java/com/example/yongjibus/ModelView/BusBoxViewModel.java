package com.example.yongjibus.ModelView;

import com.example.yongjibus.Model.BusNumber;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class BusBoxViewModel {
    String text="정보없음";
    String key="EAfT%2F%2By0QMggoerC7BknxO6OQ1dXv%2B7%2FVyNv4jt5gKzX2clBt2v%2BFEUkd2x8OUwgZbZoOZoKyGxZquu%2FBVy8fg%3D%3D";
    String stationId = "228002023";
    private String routeId="";
    String result=null;
    String OriginalUrl="https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem";

    public void load(BusNumber route){
        switch(route){
            case three:
                routeId="228000182";
                break;
            case zero:
                routeId="228000174";
                break;
            case one:
                routeId="228000177";
                break;
        }
        String testUrl="https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem?serviceKey=EAfT%2F%2By0QMggoerC7BknxO6OQ1dXv%2B7%2FVyNv4jt5gKzX2clBt2v%2BFEUkd2x8OUwgZbZoOZoKyGxZquu%2FBVy8fg%3D%3D&stationId=228002023&routeId=\\\\("+routeId+")";
        try{
            URL url=new URL(testUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while((line = reader.readLine())!=null) {
                builder.append(line);
            }

            result = builder.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
        System.out.println(testUrl);

    }
}
