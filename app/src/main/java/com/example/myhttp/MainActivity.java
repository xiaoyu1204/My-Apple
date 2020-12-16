package com.example.myhttp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhttp.ui.CheckBoxActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 点击选择true/false提交
     */
    private Button mBtnCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        mBtnCheckbox = (Button) findViewById(R.id.btn_checkbox);
        mBtnCheckbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_checkbox:
                startActivity(new Intent(this, CheckBoxActivity.class));
                break;
        }
    }

}