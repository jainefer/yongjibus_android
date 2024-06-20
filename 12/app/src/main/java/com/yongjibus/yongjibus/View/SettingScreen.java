package com.yongjibus.yongjibus.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.yongjibus.R;


public class SettingScreen extends Fragment {
    TextView email;
    SwitchCompat timeSwitch;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_setting_screen, container, false);
        preferences=this.getContext().getSharedPreferences("s1", Context.MODE_PRIVATE);
        editor=preferences.edit();
        email=view.findViewById(R.id.email);
        timeSwitch=view.findViewById(R.id.switch1);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                String[] address= {"jy5849@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL,address);
                startActivity(email);
            }
        });
        String state=preferences.getString("time","day");
        if(state.equals("day"))
            timeSwitch.setChecked(false);
        else
            timeSwitch.setChecked(true);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editor.putString("time","weekend").apply();
                    System.out.println("주말");
                }else{
                    editor.putString("time","day").apply();
                    System.out.println("평일");
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}