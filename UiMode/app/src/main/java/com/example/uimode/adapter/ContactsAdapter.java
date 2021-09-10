package com.example.uimode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uimode.R;
import com.example.uimode.mode.ContactEntity;
import com.example.uimode.mode.PersonMsg;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private  Context context;
    private ArrayList<ContactEntity> personMsgs;
    private String tips="";
    private int mPostion=0 ;

    public ContactsAdapter(Context context, ArrayList<ContactEntity> personMsgs){
        this.context = context;
        this.personMsgs = personMsgs;

    }


    // 获取到数据进行更新
    public void updateData(ArrayList<ContactEntity> persons) {
        personMsgs.clear();
        personMsgs.addAll(persons);
        notifyDataSetChanged(); //刷新
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(personMsgs.get(position).getName());
        holder.phone.setText(personMsgs.get(position).getPhone());
        holder.num.setText("位置:"+position);

        if (personMsgs.get(position).isFirst()){
            tips = personMsgs.get(position).getFirstWord();
            holder.tips_table.setText(tips);
            holder.tips_table.setVisibility(View.VISIBLE);
        }else {
            holder.tips_table.setVisibility(View.GONE);
        }
        
        if(personMsgs.get(position).isSelector()){
            holder.select_state.setImageResource(R.mipmap.selector_ture);
        }else {
            holder.select_state.setImageResource(R.mipmap.selector_false);
        }
        
        
    }

    @Override
    public int getItemCount() {
        return personMsgs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    //设置当前第一个位置的数据
    public void setSeletedPostion(int mpostion){
            this.mPostion = mpostion;

    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView headImg;
        ImageView select_state;
        TextView  name;
        TextView  phone;
        TextView  add_btn;
        TextView  add_status;
        TextView  tips_table;
        TextView  num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            select_state = itemView.findViewById(R.id.select_state);
            headImg = itemView.findViewById(R.id.headImg);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            add_btn = itemView.findViewById(R.id.add_btn);
            add_status = itemView.findViewById(R.id.add_status);
            tips_table = itemView.findViewById(R.id.tips_table);
            num = itemView.findViewById(R.id.num);
        }
    }
}
