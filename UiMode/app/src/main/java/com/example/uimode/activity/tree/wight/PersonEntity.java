package com.example.uimode.activity.tree.wight;

import android.view.View;
import java.util.List;

/**
 * Created by wg on 2017/4/24.
 * 父亲孩子表示法
 */

public class PersonEntity extends PersonData {

    // 父亲节点信息
    public PersonEntity father;
    // 子节点信息
    public List<PersonEntity> childs;

    public View.OnClickListener clickListener;

    public PersonEntity(String name, int gender, long id, int level, String avatar, String relationship) {
        super(name, gender, id, level, avatar, relationship);
    }


}
