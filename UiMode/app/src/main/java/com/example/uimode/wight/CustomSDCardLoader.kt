package com.example.uimode.wight

import android.content.Context
import android.util.Log
import skin.support.load.SkinSDCardLoader
import skin.support.utils.SkinFileUtils
import java.io.File


class CustomSDCardLoader : SkinSDCardLoader() {

    companion object {
        const val SKIN_LOADER_STRATEGY_SDCARD = Int.MAX_VALUE
    }

    override fun getSkinPath(context: Context, skinName: String): String {
        var path =   File(SkinFileUtils.getSkinDir(context), skinName).absolutePath
        Log.i("11111","12121------------>path:${path}")
        return path
    }

    override fun getType(): Int {
        return SKIN_LOADER_STRATEGY_SDCARD
    }


}