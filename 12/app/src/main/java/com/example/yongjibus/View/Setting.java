package com.example.yongjibus.View;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yongjibus.R;

public class Setting extends Dialog {

    private ImageView shutdownClick;
    public Setting(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_setting);
        shutdownClick = findViewById(R.id.close);
        shutdownClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

}