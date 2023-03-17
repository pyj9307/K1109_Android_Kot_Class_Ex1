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
import com.example.test17_test.databinding.ActivityLoginBinding
import com.example.test17_test.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var filePath: String
    // 이메일 문자열을 담는 배열
    var datas: MutableList<String>? = null
    // 패스워드 문자열을 담는 배열
    var datas2: MutableList<String>? = null
    // MyAdapter 클래스명으로 만들었음. 리사이클러뷰 사용하기 위해서 필요한 재료
    lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카메라작동 런처
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        // 카메라버튼 동작
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "LSY${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test17_test.fileprovider",
                //com.example.test17.fileprovider
                file
            )

            // getSharedPreferences : 공유 프레퍼런스 파일에 값을 저장하는 부분.
            // imgLoadTest 파일의 이름으로 저장
            val pref = getSharedPreferences("imgLoadTest", Context.MODE_PRIVATE)
            // 키, 값의 형태로 저장하는 방식
            // commit을 하게 되면 실제 공유 프레퍼런스 저장소 파일에 저장.
            pref.edit().run {
                putString("imgfileUri", photoURI.toString())
                putString("imgfile", filePath)
                commit()
            }
            val resultStr2 : String? = pref.getString("imgUri","값이 없으면 디폴트값이 옵니다.")
            val result3 = resultStr2.toString()
            Log.d("lsy","imgInfo result3 결과 : $resultStr2")
            Log.d("lsy","imgInfo result3 결과 : $result3")

            val uriTest = Uri.fromFile(File(filePath))
            Log.d("lsy"," filePath 경로 찍어보기"+uriTest.toString())
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

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