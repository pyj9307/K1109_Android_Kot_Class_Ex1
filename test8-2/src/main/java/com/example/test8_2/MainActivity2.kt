package com.example.test8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import com.example.test8_2.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    // 두번째 방법 실행 시 재정의해야하는 함수
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("lsy", "체크박스 클릭")
    }

    // 익명함수 onCheckedChanged를 재정의
    class MyEventHandler : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            Log.d("lsy", "체크박스 클릭")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var state : Int = 0
        var imagestate : Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//         먼저 바인딩을 해줘야 함(activity_main2.xml의 이름을 ActivityMain2로 바꿈
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        // 3번째 SAM 기법(single abstract method)
        binding.checkbox.setOnCheckedChangeListener {
                a, b ->
            if(state == 0) {
                binding.button.visibility = View.INVISIBLE
                Log.d("lsy", "체크박스 클릭, 버튼 안보이게")
                state = 1}
            else{
                binding.button.visibility = View.VISIBLE
                Log.d("lsy", "체크박스 클릭, 버튼 보이게")
                state = 0
            }
        }

        binding.button1.setOnLongClickListener{
            if(imagestate == 0) {
                binding.img1.visibility = View.INVISIBLE
                Log.d("lsy", "버튼 클릭, 그림 안보이게")
                imagestate = 1
            true}
            else{
                binding.img1.visibility = View.VISIBLE
                Log.d("lsy", "버튼 클릭, 그림 보이게")
                imagestate = 0
                true
            }
        }

        // 2번째 방법
//        binding.checkbox.setOnCheckedChangeListener(this)
//        val test = MyEventHandler()
//        test.onCheckedChanged(binding.checkbox, true)

        // 1번째 방법
//        val test = MyEventHandler()
//        binding.checkbox.setOnClickListener{
////             위에서 재정의된 onCheckedChanged를 불러씀
//            test.onCheckedChanged(binding.checkbox,true)
//        binding.checkbox.setOnCheckedChangeListener(MyEventHandler())
//       }
    }
}

