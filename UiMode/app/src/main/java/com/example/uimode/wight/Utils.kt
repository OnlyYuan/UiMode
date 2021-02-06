package com.example.uimode.wight

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
    }
}