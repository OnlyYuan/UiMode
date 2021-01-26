package com.example.uimode.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uimode.R;

public class DragListViewdapter extends BaseAdapter {
    ViewHolder viewHolder =null;
    int[] mPics;
    private Context context;

    public DragListViewdapter(Context context, int[] mPics){
        this.context =context;
        this.mPics = mPics;
    }
    @Override
    public int getCount() {
        return mPics.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.drag_item, null);
            viewHolder.dragImage = convertView.findViewById(R.id.drag_item_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dragImage.setImageResource(mPics[position]);

        return convertView;
    }

      class  ViewHolder{

          ImageView dragImage;
    }
}
