package com.example.uimode.mode

import android.os.Parcelable

 class PersonMsg  {

  var  name:String = ""
      get() {
         return  field
      }
      set(value) {
          field =value
      }

    var  num:String = ""
        get() {
          return field
      }
      set(value) {
          field =value
      }

    var  userId:String = ""
        get() {
          return field
      }
      set(value) {
          field =value
      }

     var firstWord:String =""
         get() {
             return field
         }
         set(value) {
             field =value
         }

     //是否是第一个字母
     var isFirst:Boolean =false
         get() {
             return field
         }
         set(value) {
             field =value
         }
}