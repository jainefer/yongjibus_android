package com.yongjibus.yongjibus.Progress;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.yongjibus.R;


import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

public class ProgressDialog extends Dialog { //커스텀 다이얼로그
    public ProgressDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 다이얼로그 제목 안 보이게
        setContentView(R.layout.dialog_progress);
    }
}