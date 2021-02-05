package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.uimode.R;
import com.example.uimode.fragment.Fragment1;
import com.example.uimode.fragment.Fragment2;

import java.util.ArrayList;
import java.util.List;

public class ThreeMenuActivity extends AppCompatActivity {

    private List<Fragment> list = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_menu);

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();


        list.add(fragment1);
        list.add(fragment2);

    }
}
