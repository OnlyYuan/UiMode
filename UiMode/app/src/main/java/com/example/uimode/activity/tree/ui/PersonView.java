package com.example.uimode.activity.tree.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.uimode.R;
import com.example.uimode.activity.tree.mode.FamilyMemberModel;
import com.example.uimode.activity.tree.wight.CombinedBaseView;


/**
 * Created by wg on 2017/4/20.
 * 人节点view
 */

public class PersonView extends CombinedBaseView {

    FamilyMemberModel familyMemberModel;

    public FamilyMemberModel getFamilyMemberModel() {
        return familyMemberModel;
    }

    public void setFamilyMemberModel(FamilyMemberModel familyMemberModel) {
        this.familyMemberModel = familyMemberModel;

        setTitle(familyMemberModel.getTreeNodeEntity().getName());
        setImage(familyMemberModel.getTreeNodeEntity().getSex(), familyMemberModel.getTreeNodeEntity().getPicUrlString());

    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonView(Context context) {
        super(context);
    }

    @Override
    protected int layoutResource() {
        return R.layout.tree_mode_layout;
    }

    @Override
    protected void onCreate(Context context) {

    }

    public static PersonView getPersonView(Context context) {
        return new PersonView(context);
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(title);
    }

    public void setTitleColor(int color) {
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setImage(int gender, String image) {
        ImageView ivHead = findViewById(R.id.ivHead);
        if (TextUtils.isEmpty(image)) {
            if (1 == gender) {
                ivHead.setImageResource(R.mipmap.ic_launcher);
            } else {
                ivHead.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.override(100, 100).centerCrop();
            if (1 == gender) {
                requestOptions.placeholder(R.mipmap.ic_launcher);
            } else {
                requestOptions.placeholder(R.mipmap.ic_launcher);
            }
            Glide.with(this)
                    .load(image)
                    .apply(requestOptions)
                    .into(ivHead);
        }
    }
}
