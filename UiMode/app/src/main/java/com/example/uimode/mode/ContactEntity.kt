package com.example.uimode.mode

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(var name:String,
                         var phone:String,
                         var pic:String,
                         var userId:String,
                         var firstWord:String,
                         var isFirst:Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id:Long =0

    /**
     *  是否选中
     *  Ignore 表示忽略不加入表中
     */
    @Ignore
    var isSelector:Boolean = false
}