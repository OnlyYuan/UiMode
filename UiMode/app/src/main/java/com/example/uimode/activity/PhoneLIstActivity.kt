package com.example.uimode.activity

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uimode.R
import com.example.uimode.adapter.ContactsAdapter
import com.example.uimode.mode.ContactEntity
import com.example.uimode.mode.api.ContactDao
import com.example.uimode.wight.AppDatabase
import com.example.uimode.wight.SideBarView
import com.example.uimode.wight.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import java.lang.reflect.Type

class PhoneLIstActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mrecyclerView: RecyclerView
    lateinit var persionMsg: ContactEntity
    lateinit var mContracts: ArrayList<ContactEntity>
    lateinit var mContracts2: ArrayList<ContactEntity>
    lateinit var sideBarView: SideBarView
    lateinit var adapter: ContactsAdapter
    var list = arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#")
    var beginTime: Long = 0
    var endTime: Long = 0
    lateinit var firstMap: HashMap<String, Int>

    lateinit var layoutManager: LinearLayoutManager
    lateinit var search_btn: EditText
    lateinit var selectorTips: TextView
    lateinit var addBtn: TextView

    //数据库Dao
    lateinit var  contactDao:ContactDao


    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {

                1 -> {
                    var count = 0
                    mContracts2 = msg.data.getSerializable("mContracts") as ArrayList<ContactEntity>
                    count = msg.data.getInt("size")
                    adapter.updateData(mContracts2);
                    endTime = System.currentTimeMillis()
                    var final = (endTime - beginTime) / 1000
                    Log.i("1111", "------->耗时：${final}")
                    for (i in 0 until count) {
                        Log.i("1111", "===>姓名:${mContracts[i].name}  手机号:${mContracts[i].phone}  userid:${mContracts[i].userId}")
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
        addBtn.setOnClickListener(this)
        search_btn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                Log.i("1111", "=====>输入：${s.toString()}")
            }
        })
    }

    /**
     * 检查权限
     */
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), 100)

        } else {
            isUpdateVersion()
        }
    }

    private fun initData() {
        contactDao = AppDatabase.getDataBase(this).contactDao()
        firstMap = HashMap()
        mContracts2 = ArrayList()
        adapter = ContactsAdapter(this@PhoneLIstActivity, mContracts2);
        layoutManager = LinearLayoutManager(this)
        mrecyclerView.layoutManager = layoutManager
        mrecyclerView.adapter = adapter
    }


    private fun initView() {
        search_btn = findViewById(R.id.search_btn)
        mrecyclerView = findViewById(R.id.recyclerView)
        sideBarView = findViewById(R.id.sideBar)
        selectorTips = findViewById(R.id.selector_tips)
        addBtn = findViewById(R.id.add_confirm_button)


        sideBarView.setContentDataList(list)
        sideBarView.setEqualItemSpace(false)
        sideBarView.itemSpace(0)

        sideBarView.setOnClickListener(object : SideBarView.OnClickListener {
            override fun onItemDown(position: Int, itemContent: String?) {

                //或者当前的字符
                var mPosition = firstMap[itemContent]
                Toast.makeText(this@PhoneLIstActivity,"mPosition: "+mPosition,Toast.LENGTH_SHORT).show()
                Log.i("11", "--------->点击了${itemContent} 位置${mPosition}")
                if (mPosition != null) {
                     adapter.setSeletedPostion(mPosition)
                    layoutManager.scrollToPositionWithOffset(mPosition, 0)
                }

            }

            override fun onItemMove(position: Int, itemContent: String?) {
                //或者当前的字符
                var mPostion = firstMap[itemContent]

                Log.i("11", "--------->点击了${itemContent} 位置${mPostion}")
                if (mPostion != null) {
                     adapter.setSeletedPostion(mPostion)
                     layoutManager.scrollToPositionWithOffset(mPostion, 0)
                }
            }

            override fun onItemUp(position: Int, itemContent: String?) {

            }

        })

    }


    /**
     * 获取 通讯录的列表
     */
    private fun getContacts(): ArrayList<ContactEntity> {
        val projectionString = arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        )

        val projectionString2 = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER
        )


        beginTime = System.currentTimeMillis()

        var contacts = ArrayList<ContactEntity>()
        contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                projectionString,
                null,
                null,
                ContactsContract.Contacts.SORT_KEY_PRIMARY
        )?.use {

            while (it!!.moveToNext()) {
                var mPersionMsg: ContactEntity = ContactEntity("","","","","", isFirst = false)
                var contactId: String = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                var name: String = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                    contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            projectionString2,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                            null,
                            null
                    )?.use { it1 ->
                        while (it1!!.moveToNext()) {
                            mPersionMsg.name = name
                            mPersionMsg.userId = contactId

                            var phoneNum: String = it1.getString(it1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            phoneNum = phoneNum.replace("-", "")
                            phoneNum = phoneNum.replace(" ", "")
                            if (phoneNum != null) {
                                mPersionMsg.phone = phoneNum
                            }
                            if (mPersionMsg.phone != null && mPersionMsg.phone != "") {
                                contacts.add(mPersionMsg)
                            }
                        }
                        it1.close()
                    }
            }
            it.close()
            Log.i("11","1=====>: list总数： ${contacts.size} ")

          for (i in 0 until contacts.size){
              Log.i("11","1=====>: 姓名： ${contacts[i].name}  电话： ${contacts[i].phone}")
          }
            getPinyin(contacts)
        }
        return contacts
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            isUpdateVersion()
        }
    }


    /**
     * 输出读取的数据
     */
    private fun showMsg() {

            mContracts = getContacts()
            Log.i("222","-->showmsg大小：${mContracts.size}")
            saveData(mContracts)

            var message = Message()
            var bundle = Bundle()
            mContracts.size
            bundle.putSerializable("mContracts", mContracts)
            bundle.putInt("size", mContracts.size)
            message.data = bundle
            message.what = 1
            handler.sendMessage(message)


    }


    /**
     * 获取首字母
     */
    private fun getPinyin(list: ArrayList<ContactEntity>): ArrayList<ContactEntity> {
        var isFirstTent = ""
        var fPinyin = " "
        var fWord = " "
        var isOther = true

        var format = HanyuPinyinOutputFormat()
        format.toneType = HanyuPinyinToneType.WITHOUT_TONE
        format.caseType = HanyuPinyinCaseType.UPPERCASE
        //  var format = HanyuPinyinOutputFormat()
        for (i in 0 until list.size) {
            fPinyin = " "
            fWord = (list[i].name[0].toString())
            if (fWord.matches(Regex("[\\u4E00-\\u9FA5]+"))) {//是汉字
                fPinyin = PinyinHelper.toHanYuPinyinString(fWord, format, null, true)

                Log.i("1111", "${fPinyin}:------------->${fPinyin}")

                if (isFirstTent != fPinyin[0].toString()) {
                    Log.i("1111", ":-1212121212------------>${i}")
                    list[i].isFirst = true

                    //记录字母首次出现的位置
                    firstMap[fPinyin[0].toString()] = i
                } else {
                    list[i].isFirst = false
                }
                isFirstTent = fPinyin[0].toString()

            } else {//其他
                list[i].firstWord = "#"
                isFirstTent = "#"
                Log.i("222","--->其他")
                if (isOther) {
                    Log.i("222","--->其他1")
                    isOther = false
                    //记录字母首次出现的位置
                    firstMap[isFirstTent] = i
                    list[i].isFirst = true
                } else {
                    Log.i("222","--->其他2")
                    list[i].isFirst = false
                }

            }
            list[i].firstWord = isFirstTent
        }
        firstMap.forEach{
            Log.i("222","----->map:${it.key}..${ it.value}")
        }

        saveFirstMap(firstMap)
        return list
    }



    /**
     * 获取通讯录数据库版本号
     */
    private fun isUpdateVersion(){

        var versionString = StringBuffer()
        var vesion:String =""
        val projectionString = arrayOf(
                "version"
        )
        lifecycleScope.launch(Dispatchers.IO) {
            contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,
                    projectionString,
                    null,
                    null,
                    null)?.use {
                while (it.moveToNext()) {
                    vesion = it.getString(it.getColumnIndex(ContactsContract.RawContacts.VERSION))
                    versionString.append(vesion)

                }
                it.close()
                Log.i("222", "--->版本号${versionString}")

                //转换为md5格式
                var mMd5 = Utils.getMD5(versionString.toString())
                Log.i("22222", "------>mMd5${mMd5}")
                //判断是否有更新
                var sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
                var preMd5 = sharedPreferences.getString("md5", "")
                Log.i("22222", "------>preMd5${preMd5}")
                if (mMd5 != preMd5) {//表示数据库有更新,把最新的值存到sharedPreferences
                    Log.i("22222", "------>数据库有更新")
                    var sharedPreferences2 = getSharedPreferences("data", Context.MODE_PRIVATE)
                    var editor = sharedPreferences2.edit()
                    editor.putString("md5", mMd5)
                    editor.commit()
                    showMsg()

                } else {
                    Log.i("22222", "------>数据库没更新")

                    mContracts = getContactData()
                    Log.i("22222", "------>getContactData=:${getContactData().size}")
                    var message = Message()
                    var bundle = Bundle()
                    getFirstMap()
                    bundle.putSerializable("mContracts", mContracts)
                    bundle.putInt("size", mContracts.size)
                    message.data = bundle
                    message.what = 1
                    handler.sendMessage(message)
                }

            }
        }
    }


    /**
     * 将读取到都的数据传入到数据库中
     */
   private fun saveData(list: ArrayList<ContactEntity>){
        Log.i("222","存入到数据库的大小：${list.size}")
        for (i in 0 until list.size){
            contactDao.insertContact(list[i])
        }
    }


    /**
     * 读取存在数据库中的联系人数据
     */
    private fun getContactData():ArrayList<ContactEntity>{
      //  Log.i("222","------>读取存在数据库中的联系人数据：${contactDao.loadAllContact().size as ArrayList<ContactEntity>}")
        var m = contactDao.loadAllContact() as ArrayList<ContactEntity>
          Log.i("222","------>读取存在数据库中的联系人数据：${m.size}")
        for(element in m){
            Log.i("222","---->读出： ${element}")
        }
        return m
    }


    /**
     * 读取首字母表的shareprefaces
     */
    private fun getFirstMap(){
        var sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("first_map","")
        val type = object : TypeToken<HashMap<String?, Int?>?>() {}.type
        var gson = Gson()
        firstMap.clear()
        firstMap = gson.fromJson(json,type)
    }


    /**
     * 存首字母map到shareprefaces
     */
    private fun saveFirstMap(map:HashMap<String,Int>){
        var gson = Gson()
        val json = gson.toJson(map)
        Log.i("222","------>josn值：${json}")
        var sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("first_map",json)
        edit.apply()

    }

    override fun onClick(v: View?) {
       when(v){
//           addBtn ->{//确定
//            Log.i("222","--->btn")
//
//           }


       }
    }


}