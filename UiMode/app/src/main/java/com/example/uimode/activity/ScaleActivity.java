package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.uimode.R;
import com.jarvislau.destureviewbinder.GestureViewBinder;

/**
 * 缩放
 */
public class ScaleActivity extends AppCompatActivity {
    private ScaleGestureDetector mScaleGestureDetector = null;
    private RelativeLayout m_layout;
    private LinearLayout l_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_scale);
       String hanzi ="";
//        hanzi.matches("[\\u4E00-\\u9FA5]+");
        m_layout = findViewById(R.id.m_layout);
        l_layout = findViewById(R.id.l_layout);
//        GestureViewBinder.bind(this, m_layout, l_layout);
//        String[]  projectionString = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
//                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Phone.NUMBER};
    }

}