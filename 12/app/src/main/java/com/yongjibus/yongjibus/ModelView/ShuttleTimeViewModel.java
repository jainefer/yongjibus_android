package com.yongjibus.yongjibus.ModelView;







import android.content.Context;
import android.content.res.AssetManager;

import com.yongjibus.yongjibus.Adapter.ShuttleViewItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ShuttleTimeViewModel {

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
            jsonArray = jsonObject.getJSONArray("Times");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public ArrayList<ShuttleViewItem> getJsonString(Context context, String state){
        ArrayList<ShuttleViewItem> stDataList=new ArrayList<ShuttleViewItem>();
        JSONArray shuttleArray;
        if(state.equals("day")) {
             shuttleArray = loadJSONArrayFromAsset(context, "StationTimeTable.json");
        }else{
             shuttleArray = loadJSONArrayFromAsset(context, "WeekendTimeTable.json");
        }
       try{

           for(int i=0; i < shuttleArray.length();i++){

               JSONObject subJsonObject = shuttleArray.getJSONObject(i);
               String stNumber= subJsonObject.getString("순번");
               String stName= subJsonObject.getString("운행 구분");
               String stStart = subJsonObject.getString("출발 시각");
               String stArrive = subJsonObject.getString("진입로경유 예정 시간");
               ShuttleViewItem item = new ShuttleViewItem(stNumber,stName,stStart,stArrive);
               stDataList.add(0,item);
;           }
       }catch (JSONException e){
           e.printStackTrace();
       }
       return stDataList;
    }

}
