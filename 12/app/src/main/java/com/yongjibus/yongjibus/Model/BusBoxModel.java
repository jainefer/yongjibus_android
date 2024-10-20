package com.yongjibus.yongjibus.Model;



import android.app.Activity;


import com.example.yongjibus.BuildConfig;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class BusBoxModel {

    public interface OnBusArrivalListener {
        void onBusArrival(String arrivalTime);
    }
    String text="정보없음";
    String key= BuildConfig.MY_KEY;
    String stationId = "228002023";
    private String routeId="";
    String result=null;
    String OriginalUrl="https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem";
    private String ariveBus;
    private Activity activity;
    public BusBoxModel(Activity activity) {
        this.activity = activity;
    }
    public void load(BusNumber route, OnBusArrivalListener listener){
        switch(route){
            case three:
                routeId="228000430";
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
                try {
                    System.out.println("route: "+route+" routeId:"+routeId);
                    String apiUrl = "https://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalItem";
                    StringBuilder urlBuilder = new StringBuilder(apiUrl);
                    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key);
                    urlBuilder.append("&" + URLEncoder.encode("stationId", "UTF-8") + "=" + URLEncoder.encode(stationId, "UTF-8"));
                    urlBuilder.append("&" + URLEncoder.encode("routeId", "UTF-8") + "=" + URLEncoder.encode(routeId, "UTF-8"));

                    URL url = new URL(urlBuilder.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        try (InputStream is = conn.getInputStream()) {
                            // XML 파서 초기화
                            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = parserFactory.newPullParser();
                            parser.setInput(is, null);

                            String predictTime1 = null;
                            String predictTime2 = null;

                            // XML 파싱
                            int eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String tagName = parser.getName();
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        if ("predictTime1".equals(tagName)) {
                                            predictTime1 = parser.nextText();

                                        } else if ("predictTime2".equals(tagName)) {
                                            predictTime2 = parser.nextText();
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }

                            if (predictTime1 != null) {
                                ariveBus = predictTime1;
                                String finalPredictTime = predictTime1;
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onBusArrival(finalPredictTime);
                                    }
                                });
                            }
                            else{
                                ariveBus = "-1";
                                String finalPredictTime = ariveBus;
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onBusArrival(finalPredictTime);
                                    }
                                });
                            }
                            routeId=" ";
                        }
                    } else {
                        // 오류 처리
                        // conn.getErrorStream()를 사용하여 오류 응답을 처리
                    }
                    Thread.sleep(1000);
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                    // 예외 처리
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();




    }
}
