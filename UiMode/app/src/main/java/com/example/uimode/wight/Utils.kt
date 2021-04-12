package com.example.uimode.wight

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.security.MessageDigest
import kotlin.experimental.and

open class Utils {


    companion object{
    /**
     *  转md5
     */
         fun getMD5(string: String): String {
            var mbyte = string.toByteArray()
            var md5 = ""
            var sbString = StringBuffer()

            var messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(mbyte)
            var temp = messageDigest.digest()

            for (b in temp) {
                //  sbString.append(Integer.toHexString(temp[i] & 0xff))
                sbString.append(Integer.toHexString(b.toInt() and 0xff))
            }
            md5 = sbString.toString()
            return md5
        }



//        private void saveHashMap(HashMap<String,PeopleBean> map){
//
//            Gson gson = new Gson();
//            String json = gson.toJson(map);
//
//            //步骤1：创建一个SharedPreferences对象
//            SharedPreferences sharedPreferences= getSharedPreferences("config", Context.MODE_PRIVATE);
//            //步骤2： 实例化SharedPreferences.Editor对象
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            //步骤3：将获取过来的值放入文件
//            editor.putString("config",json);
//            editor.commit();
//
//
//        }

    }
}