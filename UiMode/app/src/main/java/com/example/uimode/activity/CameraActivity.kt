package com.example.uimode.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityCameraBinding
import com.example.uimode.tools.PermissionUtils
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * 拍照
 */
class CameraActivity:Activity() {

    lateinit var cameraBinding: ActivityCameraBinding
    val CAMERA_PERMSSION_CODE: Int = 1
    val CAMERA_REQUEST_CODE:Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera)
        checkPermission();
        cameraBinding.cameraBtn.setOnClickListener {
            takepicture();
        }
        this@CameraActivity.packageManager
    }

    /**
     * 获取权限
     */
    private fun checkPermission() {

        PermissionUtils.with(this,this)
            .permission(android.Manifest.permission.CAMERA)
            .permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .permission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .request(object : PermissionUtils.OnPermissionCheckCallback{
                override fun onCheckGranted() {
                    super.onCheckGranted()
                    Toast.makeText(this@CameraActivity,"已经授权",Toast.LENGTH_LONG).show()
                    //takepicture()
                }
            })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode==PermissionUtils.PERMISSION_CODE){
               // takepicture();
            }else{
                Toast.makeText(this@CameraActivity,"授权失败",Toast.LENGTH_SHORT).show()
            }
    }


    /**
     * 拍照
     */
    private fun takepicture() {
        openCamera();
    }


    //用于保存拍照图片的uri
    private var mCameraUri: Uri? = null
    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private var mCameraImagePath: String? = null
    // 是否是Android 10以上手机
    private val isAndroidQ = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    /**
     * 调起相机拍照
     */
    private fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 判断是否有相机
        if (captureIntent.resolveActivity(this.packageManager) != null) {
            var photoFile: File? = null
            var photoUri: Uri? = null
            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri()
            } else {
                try {
                    photoFile = createImageFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (photoFile != null) {
                    mCameraImagePath = photoFile.absolutePath
                    photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        FileProvider.getUriForFile(this, "$packageName.fileprovider", photoFile)
                    } else {
                        Uri.fromFile(photoFile)
                    }
                }
            }
            mCameraUri = photoUri
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(captureIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private fun createImageUri(): Uri? {
        val status = Environment.getExternalStorageState()
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (status == Environment.MEDIA_MOUNTED) {
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        } else {
            contentResolver.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, ContentValues())
        }
    }

    /**
     * 创建保存图片的文件
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val imageName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir!!.exists()) {
            storageDir.mkdir()
        }
        val tempFile = File(storageDir, imageName)
        return if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            null
        } else tempFile
    }
}