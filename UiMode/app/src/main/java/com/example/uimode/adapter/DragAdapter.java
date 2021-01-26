package com.example.uimode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uimode.R;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> {

    private Context context;
    private  int[] mPics;

    //第一步 定义接口
    public interface OnItemPostionListener {
        void onClick(int position);
    }

    private OnItemPostionListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemPostionClickListener(OnItemPostionListener listener) {
        this.listener = listener;
    }


    public DragAdapter(Context context,  int[] mPics){
        this.context = context;
        this.mPics =mPics;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.drag_item_img.setImageResource(mPics[position]);

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        listener.onClick(position);
                        break;
                }

                return false;
            }
        });

    }



    @Override
    public int getItemCount() {
        return mPics.length;
    }



    public  class  ViewHolder extends RecyclerView.ViewHolder{

      private  ImageView drag_item_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drag_item_img = itemView.findViewById(R.id.drag_item_img);
        }

    }


}
