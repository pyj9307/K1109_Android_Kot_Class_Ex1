package com.example.test17_test

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test17_test.databinding.ActivityJoinBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class JoinActivity : AppCompatActivity() {
    // 바인딩 변수 선언
    lateinit var binding: ActivityJoinBinding
    // 파일 경로를 담을 변수 선언
    lateinit var filePath: String
    lateinit var userEmail: String
    lateinit var userPass: String
    // 이메일 문자열을 담는 배열
    var datas: MutableList<String>? = null
    // 패스워드 문자열을 담는 배열
    var datas2: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카메라앱 불러서 사진 찍고 후처리 코드.
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            // 비트맵 형식으로 해당 옵션을 사용하겠다.
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            // 해당 파일 경로의 파일을 읽어서 비트맵 형으로 반환하고, 옵션 적용하기
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                // 사용자 프로필 이미지에 사진 붙이기 작업
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        // 카메라버튼 동작
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            // timeStamp에 날짜시분초로 문자열 생성
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // storageDir 안드로이드에서 자동으로 생성해주는 외부 임시 파일 경로 문자열 생성
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            // createTempFile로 prefix, suffix로 지정한 파일이름 규칙으로
            // storageDir에 담긴 경로에 파일 저장
            val file = File.createTempFile(
                "LSY${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            // FileProvider 콘텐츠 프로바이더를 이용하려면 매니페스트에 등록 및 xml 추가 파일 작업이 필요함.
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
//                putString("email_editView", binding.emailEditView.text.toString())
//                putString("pass_editView", binding.passEditView.text.toString())
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

        // 회원 가입 버튼을 눙ㄹ러서 정보를 전달하려면 인텐트를 이용해
        // 단순 이동으로 사용하겠음. 일단
        binding.joinButton.setOnClickListener{
            userEmail = binding.emailEditView.text.toString()
            userPass = binding.passEditView.text.toString()

            // 공유 프리퍼런스라는 파일에 사진의 위치정보, 이메일, 패스워드를 인자값으로
            // 전달하면 파일에 저장한다.
            upLoadUserData(filePath, userEmail, userPass)

            // 단순 이동으로만 시용
            val intent = Intent(this@JoinActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun upLoadUserData(imgFilePath:String, userEmail: String, userPass: String){
        val pref = getSharedPreferences("joinTest", Context.MODE_PRIVATE)
        // 키, 값의 형태로 저장하는 방식
        // commit을 하게 되면 실제 공유 프레퍼런스 저장소 파일에 저장.
        pref.edit().run {
            putString("email", userEmail)
            putString("pass", userPass)
            putString("imgfile", imgFilePath)
            commit()
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