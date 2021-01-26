package com.example.uimode.activity

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.uimode.R
import com.example.uimode.databinding.ActivityCameraBinding


/**
 * 拍照
 */
class CameraActivity:Activity() {

    lateinit var cameraBinding: ActivityCameraBinding
    val CAMERA_PERMSSION_CODE: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera)
        checkPermssion();
        takepicture();
        this@CameraActivity.packageManager

    }



    /**
     * 获取权限
     */
    private fun checkPermssion() {
        var hasPermission: Int = ContextCompat.checkSelfPermission(application, android.Manifest.permission.CAMERA)

        if (hasPermission == CAMERA_PERMSSION_CODE) {//有权限

            takepicture()
        } else {

            ActivityCompat.requestPermissions(this@CameraActivity, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMSSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode==CAMERA_PERMSSION_CODE){

                takepicture();
            }else{

                Toast.makeText(this@CameraActivity,"授权失败",Toast.LENGTH_SHORT).show()
            }

    }

}


/**
 * 拍照
 */
private fun takepicture() {


}

////用于保存拍照图片的uri
//private Uri mCameraUri;
//
//// 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
//private String mCameraImagePath;
//
//// 是否是Android 10以上手机
//private boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;
//
///**
// * 调起相机拍照
// */
//private void openCamera() {
//    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//    // 判断是否有相机
//    if (captureIntent.resolveActivity(()) != null) {
//        File photoFile = null;
//        Uri photoUri = null;
//
//        if (isAndroidQ) {
//            // 适配android 10
//            photoUri = createImageUri();
//        } else {
//            try {
//                photoFile = createImageFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (photoFile != null) {
//                mCameraImagePath = photoFile.getAbsolutePath();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
//                    photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
//                } else {
//                    photoUri = Uri.fromFile(photoFile);
//                }
//            }
//        }
//
//        mCameraUri = photoUri;
//        if (photoUri != null) {
//            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
//        }
//    }
//}
//
///**
// * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
// */
//private Uri createImageUri() {
//    String status = Environment.getExternalStorageState();
//    // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
//    if (status.equals(Environment.MEDIA_MOUNTED)) {
//        return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
//    } else {
//        return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
//    }
//}
//
///**
// * 创建保存图片的文件
// */
//private File createImageFile() throws IOException {
//    String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//    if (!storageDir.exists()) {
//        storageDir.mkdir();
//    }
//    File tempFile = new File(storageDir, imageName);
//    if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
//        return null;
//    }
//    return tempFile;
//}