package com.example.test10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test10.databinding.ActivityMain288Binding
import com.example.test10.databinding.DialogInputBinding

class MainActivity288 : AppCompatActivity() {
    fun ToastTest(msg:String){
        Toast.makeText(this@MainActivity288,"토스트 샘플 테스트 : ${msg}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain288Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            // DialogInputBinding 사용자가 직접 정의한 xml 파일을 바인딩했음
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)

            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root)
                setIcon(R.drawable.person1)
                dialogBinding.edit1.setOnClickListener{
                    Log.d("lsy", "에디터 뷰 선택 한번 해봄")
                    ToastTest("토스트도 연습")
                    Toast.makeText(this@MainActivity288,"male 연습",Toast.LENGTH_SHORT).show()
                }
                dialogBinding.edit2.setOnClickListener{
                    Log.d("lsy", "에디터 뷰 선택 한번 해봄")
                    ToastTest("토스트도 연습2")
                    Toast.makeText(this@MainActivity288,"female 연습",Toast.LENGTH_SHORT).show()
                }
                setPositiveButton("닫기", null)
                show()
            }
        }
    }
}