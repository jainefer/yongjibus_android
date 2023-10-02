package com.example.yongjibus.ModelView;



import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.yongjibus.Model.BusNumber;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


public class BusBoxViewModel {
    public interface OnBusArrivalListener {
        void onBusArrival(String arrivalTime);
    }
    String text="정보없음";
    String key="WwT%2FuyuSGkPvsie3TDUXl%2BygKRkRCd8Dbc%2Fa8KiWC9YMaWnZaYLeViZeAYNpfoOO7kaQbdmQcou76Hmj7CSi%2Bw%3D%3D";
    String stationId = "228002023";
    private String routeId="";
    String result=null;
    String OriginalUrl="https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem";
    private String ariveBus;
    private Activity activity;
    public BusBoxViewModel(Activity activity) {
        this.activity = activity;
    }
    public void load(BusNumber route, OnBusArrivalListener listener){
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
        new Thread() {
            public void run() {
                String testUrl = "https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem?serviceKey=EAfT%2F%2By0QMggoerC7BknxO6OQ1dXv%2B7%2FVyNv4jt5gKzX2clBt2v%2BFEUkd2x8OUwgZbZoOZoKyGxZquu%2FBVy8fg%3D%3D&stationId=228002023&routeId=\\\\(" + routeId + ")";
                try {
                    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem"); /*URL*/
                    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("stationId", "UTF-8") + "=" + URLEncoder.encode(stationId, "UTF-8")); /*정류소ID*/
                    urlBuilder.append("&" + URLEncoder.encode("routeId", "UTF-8") + "=" + URLEncoder.encode(routeId, "UTF-8")); /*노선ID*/
//            urlBuilder.append("&" + URLEncoder.encode("staOrder","UTF-8") + "=" + URLEncoder.encode("19", "UTF-8")); /*노선의 정류소 순번*/
                    URL url = new URL(urlBuilder.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");
                    System.out.println("Response code: " + conn.getResponseCode());
                    BufferedReader rd;
                    if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    } else {
                        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    }
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                    rd.close();
                    conn.disconnect();
                    Log.e("L2", sb.toString());
                    System.out.println(sb.toString());

                    XmlPullParserFactory parserFactory=XmlPullParserFactory.newInstance();
                    XmlPullParser parser = parserFactory.newPullParser();
                    String tagName="";
                    parser.setInput(new StringReader(sb.toString()));
                    String predictTime1 = null;
                    String predictTime2 = null;
                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT){
                        tagName=parser.getName();
                        switch(eventType){
                            case XmlPullParser.START_TAG:
                                    if("predictTime1".equals(tagName)){
                                        predictTime1=parser.nextText();


                                    }else if("predictTime2".equals(tagName)){
                                        predictTime2=parser.nextText();
                                    }
                                    break;
                        }
                        eventType = parser.next();
                    }

                    if(predictTime1 != null) {
                        Log.e("1번째",predictTime1);
                        ariveBus=predictTime1;
                        String finalPredictTime = predictTime1;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onBusArrival(finalPredictTime);
                            }
                        });


                    }
                    if(predictTime2!= null){
                        Log.e("2번째",predictTime2);
                    }

                } catch (ProtocolException e) {
                    throw new RuntimeException(e);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }


        }.start();



    }
}
