package com.example.myhttp.utils;

import android.text.TextUtils;
import android.widget.TextView;

//setText工具类
public class TxUtils {

    public static void setTextView(TextView textView,String word){
        if(textView != null && !TextUtils.isEmpty(word)){
            textView.setText(word);
        }
    }

}
