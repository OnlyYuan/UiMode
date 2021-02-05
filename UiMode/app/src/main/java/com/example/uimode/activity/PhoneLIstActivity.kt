package com.example.uimode.activity

import android.content.pm.PackageManager
import android.content.res.Resources
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uimode.R
import com.example.uimode.adapter.ContactsAdapter
import com.example.uimode.mode.PersonMsg
import com.example.uimode.wight.SideBarView
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType

class PhoneLIstActivity : AppCompatActivity() {
    lateinit var mrecyclerView:RecyclerView
    lateinit var persionMsg:PersonMsg
    lateinit var mContracts:ArrayList<PersonMsg>
    lateinit var mContracts2:ArrayList<PersonMsg>
    lateinit var sideBarView:SideBarView
    lateinit var adapter:ContactsAdapter
    var list= arrayListOf("A","B","C","D","E","F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#")
    var beginTime:Long = 0
    var endTime:Long =0
    lateinit var firstMap:HashMap<String,Int>

    lateinit var layoutManager:LinearLayoutManager
    lateinit var  search_btn:EditText


    var handler:Handler =object :Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){

                1->{
                    var count =0
                    mContracts2 = msg.data.getSerializable("mContracts") as ArrayList<PersonMsg>
                    count = msg.data.getInt("size")
                    adapter.updateData(mContracts2);
                    endTime = System.currentTimeMillis()
                    var final =(endTime- beginTime)/1000
                    Log.i("1111","------->耗时：${final}")
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
        initData()
        checkPermission()
        Linsener()

    }

    private fun Linsener() {

        search_btn.addTextChangedListener( object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    Log.i("1111","=====>输入：${s.toString()}")


            }


        })


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
        firstMap = HashMap()
        mContracts2 = ArrayList()
        adapter = ContactsAdapter(this@PhoneLIstActivity,mContracts2);
        layoutManager = LinearLayoutManager(this)
        mrecyclerView.setLayoutManager(layoutManager)
        mrecyclerView.adapter =adapter
    }


    private fun initView() {
        search_btn = findViewById(R.id.search_btn)
        mrecyclerView  = findViewById(R.id.recyclerView)
        sideBarView = findViewById(R.id.sideBar)
        sideBarView.setContentDataList(list)
        sideBarView.setEqualItemSpace(false)
        sideBarView.itemSpace(1)
        sideBarView.setTextColor(resources.getColor(R.color.black))
        sideBarView.setOnClickListener(object : SideBarView.OnClickListener {
            override fun onItemDown(position: Int, itemContent: String?) {

                //或者当前的字符
                var mPostion =  firstMap.get(itemContent)

                Log.i("11","--------->点击了${itemContent} 位置${mPostion}")
                if (mPostion != null) {
                   // adapter.setSeletedPostion(mPostion)
                    layoutManager.scrollToPositionWithOffset(mPostion,0)
                }

            }

            override fun onItemMove(position: Int, itemContent: String?) {
                //或者当前的字符
                var mPostion =  firstMap.get(itemContent)

                Log.i("11","--------->点击了${itemContent} 位置${mPostion}")
                if (mPostion != null) {
                    // adapter.setSeletedPostion(mPostion)
                    layoutManager.scrollToPositionWithOffset(mPostion,0)
                }
            }

            override fun onItemUp(position: Int, itemContent: String?) {

            }

        })
       // sideBarView.setOnClickListener()
    }


    /**
     * 获取 通讯录的列表
     */
    private fun getContacts():ArrayList<PersonMsg> {
        val projectionString = arrayOf(

                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                )

       val projectionString2 = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                )


         beginTime = System.currentTimeMillis()

        var contacts = ArrayList<PersonMsg>()
       var cursor: Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,projectionString,null,null,ContactsContract.Contacts.SORT_KEY_PRIMARY)
        while (cursor!!.moveToNext()){

            var mPersionMsg:PersonMsg = PersonMsg()
            var contactId:String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            var name:String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

            var phoneCursor:Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projectionString2,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null)
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
            if (mPersionMsg.num!=null&&mPersionMsg.num!=""){
                contacts.add(mPersionMsg)
            }

            phoneCursor.close()

        }
        cursor.close()
        getPinyin(contacts)
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
     * 获取首字母
     */
    private fun getPinyin(list:ArrayList<PersonMsg>):ArrayList<PersonMsg>{
        var isFirstTent =""
        var fPinyin =" "
        var fWord =" "
        var isOther=false

        var format =  HanyuPinyinOutputFormat()
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
        format.caseType = HanyuPinyinCaseType.UPPERCASE
      //  var format = HanyuPinyinOutputFormat()
        for (i in 0 until list.size){
            fPinyin =" "
            fWord = (list[i].name.get(0).toString())
            if (fWord.matches(Regex("[\\u4E00-\\u9FA5]+"))){//是汉字
                fPinyin = PinyinHelper.toHanYuPinyinString(fWord,format,null,true)

                Log.i("1111","${fPinyin}:------------->${fPinyin}")

                if (!(isFirstTent ==fPinyin.get(0).toString())){
                    Log.i("1111",":-1212121212------------>${i}")
                    list[i].isFirst = true
                    //记录字母首次出现的位置
                    firstMap.set(fPinyin.get(0).toString(),i)
                }else{
                    list[i].isFirst = false
                }
                isFirstTent = fPinyin.get(0).toString()

            }else {//其他
                list[i].firstWord ="#"

                if (isOther){
                    isOther = false
                    //记录字母首次出现的位置
                    firstMap.set(isFirstTent,i)
                    list[i].isFirst = true
                }else{
                    list[i].isFirst = false
                }
                isFirstTent = "#"
            }

            list[i].firstWord = isFirstTent

        }


        return list
    }


}