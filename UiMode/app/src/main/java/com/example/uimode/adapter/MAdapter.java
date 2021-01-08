package com.example.uimode.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uimode.R;

import java.util.List;

public class MAdapter extends BaseAdapter {
    ViewHolder viewHolder =null;
    String[] mtitile;
    private Context context;


    public  MAdapter(Context context,String[] mtitile){
        this.context =context;
        this.mtitile = mtitile;
    }
    @Override
    public int getCount() {
        return mtitile.length;
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
            convertView = View.inflate(context, R.layout.menu_item, null);
            viewHolder.itemName = convertView.findViewById(R.id.itemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemName.setText(mtitile[position]);

        return convertView;
    }

      class  ViewHolder{

          TextView itemName;
    }
}
