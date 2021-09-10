package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.uimode.R;
import com.example.uimode.databinding.ActivityKuaiShouBinding;

public class KuaiShouActivity extends AppCompatActivity {

    private ActivityKuaiShouBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_kuai_shou);
        binding.webView.loadUrl("https://video.kuaishou.com/search/video?searchKey=重庆");
    }
}