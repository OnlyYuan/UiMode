package com.example.uimode.tools

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * 权限工具
 * @author cpf
 * @date 2021/9/14
 */
open class PermissionUtils(val context: Context,val activity:Activity) {

    var mPermissions: ArrayList<String>? = ArrayList()

    companion object{

        const val PERMISSION_CODE: Int = 1

        open fun with(context:Context,activity:Activity):PermissionUtils{
                return PermissionUtils(context,activity)
         }
    }


    /**
     * 添加权限组 (vararg 动态参数 对应 java ... )
     */
    public fun permission( vararg permissions:String):PermissionUtils{

        mPermissions?.addAll(permissions.asList())
        return this
    }


    /**
     * 检查和设置权限
     */
    public fun request(onCallback:OnPermissionCheckCallback) {

        var isGranted = true
        for ( item in mPermissions!!){
            isGranted = (ContextCompat.checkSelfPermission(context,item)==PackageManager.PERMISSION_DENIED) && isGranted
        }

        if (isGranted){
            //ActivityCompat.requestPermissions(activity, mPermissions!!.toTypedArray(),PERMISSION_CODE)
            onCallback.onCheckGranted()
        }else{

            ActivityCompat.requestPermissions(activity, mPermissions!!.toTypedArray(),PERMISSION_CODE)
        }
    }


    interface OnPermissionCheckCallback {

        /**
         * 已经有权限了
         */
        fun onCheckGranted() {}
    }
}