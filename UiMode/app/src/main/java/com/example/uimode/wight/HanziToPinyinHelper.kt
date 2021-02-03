package com.example.uimode.wight

import android.util.Log
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType

class HanziToPinyinHelper {

    /**
     * 获取汉字的拼音
     */
    public fun getPinyin(hanzi:String):String{

        var pinyin:String =""
        var format =  HanyuPinyinOutputFormat()
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
        //  var format = HanyuPinyinOutputFormat()
        if (hanzi.matches(Regex("[\\u4E00-\\u9FA5]+"))){
            pinyin = PinyinHelper.toHanYuPinyinString(hanzi,format,null,true)
            Log.i("1111","${hanzi}:------------->${pinyin}")

        }

        return pinyin
    }


}