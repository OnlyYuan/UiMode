package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.uimode.R;

/**
 * 图片跟着手指拖动行进
 */
public class DragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
    }
}
