package com.yongjibus.yongjibus.ModelView;

import android.content.Context;
import android.content.res.AssetManager;

import com.yongjibus.yongjibus.Adapter.GihuengStationViewItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GihuengTimeViewModel {

    public static JSONArray loadJSONArrayFromAsset(Context context, String fileName) {
        JSONArray jsonArray = null;
        try {
            // Assets 매니저를 통해 JSON 파일을 열기
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // JSON 문자열로 변환
            String jsonStr = new String(buffer, StandardCharsets.UTF_8);

            // JSON 배열 파싱
            JSONObject jsonObject = new JSONObject(jsonStr);
            jsonArray = jsonObject.getJSONArray("StationTimes");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public ArrayList<GihuengStationViewItem> getJsonString(Context context){
        ArrayList<GihuengStationViewItem> stDataList=new ArrayList<GihuengStationViewItem>();
        JSONArray shuttleArray= loadJSONArrayFromAsset(context,"GiheungTimeTable.json");
        try{

            for(int i=0; i < shuttleArray.length();i++){

                JSONObject subJsonObject = shuttleArray.getJSONObject(i);
                String stNumber= subJsonObject.getString("id");
                String stStart= subJsonObject.getString("startTime");
                String stStopOver = subJsonObject.getString("stationArrival");
                String stArrive = subJsonObject.getString("schoolArrival");
                String stUnit=subJsonObject.getString("runCount");
                GihuengStationViewItem item = new GihuengStationViewItem(stNumber,stStart,stStopOver,stArrive,stUnit);
                stDataList.add(0,item);
                        }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return stDataList;
    }
}
