package com.example.test17_test

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test17_test.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    // 파일 경로를 담을 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 창에서 입력한 정보를 가져와서 뷰에 붙이는 작업.
        // 공유 프리퍼런스에 값 가지고 오기

        val pref = getSharedPreferences("joinTest", Context.MODE_PRIVATE)

        val userEmail : String? = pref.getString("email","값이 없으면 디폴트값이 옵니다.")
        val userPass : String? = pref.getString("pass","값이 없으면 디폴트값이 옵니다.")
        val userProfile : String? = pref.getString("imgfile","값이 없으면 디폴트값이 옵니다.")

        binding.emailEditView.setText(userEmail)
        binding.passEditView.setText(userPass)

        val calRatio = calculateInSampleSize(
            Uri.fromFile(File(userProfile)),
            resources.getDimensionPixelSize(R.dimen.imgSize),
            resources.getDimensionPixelSize(R.dimen.imgSize)
        )
        val option = BitmapFactory.Options()

        val bitmap = BitmapFactory.decodeFile(userProfile, option)
        bitmap?.let {
            binding.userImageView.setImageBitmap(bitmap)
        }
    }

    // 프로필 사진 자르기
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}