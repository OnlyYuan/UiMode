package com.example.uimode.activity

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uimode.R
import com.example.uimode.mode.PersonMsg
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType

class PhoneLIstActivity : AppCompatActivity() {
    lateinit var mrecyclerView:RecyclerView
    lateinit var persionMsg:PersonMsg
    lateinit var mContracts:ArrayList<PersonMsg>
    lateinit var mContracts2:ArrayList<PersonMsg>

    var handler:Handler =object :Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){

                1->{
                    var count =0
                    mContracts2 = msg.data.getSerializable("mContracts") as ArrayList<PersonMsg>
                    count = msg.data.getInt("size")

                    for (i in 0.. count-1){
                        Log.i("1111","===>姓名:${ mContracts[i].name}  手机号:${mContracts[i].num}  userid:${mContracts[i].userId}")
                    }

                }

            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_l_ist)

        initView()
       // initData()
        checkPermission()


    }

    /**
     * 检查权限
     */
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),100)

        }else{

            showMsg()
        }

    }


    private fun initData() {
        persionMsg = PersonMsg()
        Log.i("121"," =========>11111111111111111")
        persionMsg.name = "小王"
        persionMsg.num = "1584223321"
        persionMsg.userId = "1111"


        Log.i("121"," name:${persionMsg.name} num:${persionMsg.num} id${persionMsg.userId}")
    }


    private fun initView() {

        mrecyclerView  = findViewById(R.id.recyclerView)
    }


    /**
     * 获取 通讯录的列表
     */
    private fun getContacts():ArrayList<PersonMsg> {

        val projectionString = arrayOf(

                ContactsContract.Contacts._ID,
                //ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.DISPLAY_NAME,

        )


       var contacts = ArrayList<PersonMsg>()
       var cursor: Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,projectionString,null,null,null)
        while (cursor!!.moveToNext()){

            var mPersionMsg:PersonMsg = PersonMsg()
            var contactId:String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            var name:String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

            var phoneCursor:Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projectionString,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null)
            mPersionMsg.name = name
            mPersionMsg.userId =contactId


            while (phoneCursor!!.moveToNext()){
                var phoneNum :String = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                phoneNum = phoneNum.replace("-","")
                phoneNum = phoneNum.replace(" ","")
                if (phoneNum!=null){
                    mPersionMsg.num =phoneNum
                }

            }

            contacts.add(mPersionMsg)
            phoneCursor.close()

        }
        cursor.close()

      return  contacts
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==100){
            showMsg()
        }
    }

    /**
     * 输出读取的数据
     */
    private fun showMsg(){
        Thread{
            mContracts = getContacts()

            var message = Message()
            var bundle = Bundle()

            mContracts.size
            bundle.putSerializable("mContracts",mContracts)
            bundle.putInt("size", mContracts.size)
            message.data =bundle
            message.what=1
            handler.sendMessage(message)

        }.start()
    }


    /**
     * 按首字母排序
     */
    private fun sortByLetter(mPersionMsgList:ArrayList<PersonMsg>){
        mPersionMsgList
    }


    /**
     * 获取汉字的拼音
     */
    private fun getPinyin(list:ArrayList<PersonMsg>):ArrayList<PersonMsg>{

        var fPinyin =" "
        var fWord =" "
        var format =  HanyuPinyinOutputFormat()
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
      //  var format = HanyuPinyinOutputFormat()
        for (i in 0 until list.size){
            fPinyin =" "
            fWord = (list[i].name.get(0).toString())
            if (fWord.matches(Regex("[\\u4E00-\\u9FA5]+"))){//是汉字
                fPinyin = PinyinHelper.toHanYuPinyinString(fWord,format,null,true)
                Log.i("1111","${fPinyin}:------------->${fPinyin}")
                list[i].firstWord = fPinyin
            }else {

                list[i].firstWord = "#"
            }
        }

        return list
    }


}