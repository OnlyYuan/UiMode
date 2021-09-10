package com.example.uimode.activity

import android.content.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityBroadcastBinding


/**
 * broadcast 广播demo
 *
 */
class BroadcastActivity : AppCompatActivity() {

    lateinit var binding:ActivityBroadcastBinding
    lateinit var broadcastReceiver: MyBroadcastReceiver
    val BROADCAST_TIPS = "com.cpf.broad.flag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_broadcast)

        //采用静态注册
        // initReceiver()

        binding.btn.setOnClickListener {
            Log.i("11","-->点击了")
            sendBroadcastFun()
        }
    }

    private fun sendBroadcastFun() {
        val intent = Intent(BROADCAST_TIPS)
        // 8.0后 静态注册广播必须 ComponentName的第一个参数是自定义广播的包名，第二个参数是广播接收器的类
        intent.setPackage("com.example.uimode.activity")
        intent.setAction("com.cpf.broad.flag")
        intent.putExtra("msg","传递成功！")

        sendBroadcast(intent)

    }

    /**
     * 1.动态注册
     */
    private fun initReceiver() {
        broadcastReceiver = MyBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.cpf.broad.flag")
        registerReceiver(broadcastReceiver,intentFilter)
    }


    class MyBroadcastReceiver :BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("11","--->mmm}")
           when(intent?.action){
               "com.cpf.broad.flag" ->{
                        Log.i("11","--->${intent.getStringExtra("msg")}")
                }
           }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        //2。记得销毁
        unregisterReceiver(broadcastReceiver)
    }

}