package com.example.k1109_android_kot_class_ex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.k1109_android_kot_class_ex1.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        // 액티비티 화면 출력
        setContentView(binding.root)
        // 뷰 객체 이용
        binding.image2.setOnClickListener (){
            Log.d("click", "버튼 클릭 테스트중");
            binding.image2.visibility = View.INVISIBLE
        }
        val rid : Int = R.id.textView


        }
    }

private fun ImageView.setOnClickListener(d: Int) {

}


