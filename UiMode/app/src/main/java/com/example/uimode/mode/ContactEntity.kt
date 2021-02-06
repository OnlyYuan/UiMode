package com.example.uimode.mode

import androidx.room.Entity
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

}