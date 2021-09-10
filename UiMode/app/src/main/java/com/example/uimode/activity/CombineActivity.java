package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.uimode.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 正式效果
 */
public class CombineActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        String m = "c";
        String f = m.toUpperCase();
        Log.i("11","---->f："+f);
        initView();
    }

    private void initView() {
        viewPager2 = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
    }

    private void fun(){
       // new TabLayoutMediator(tabLayout,viewPager2,).attach();

    }


}
