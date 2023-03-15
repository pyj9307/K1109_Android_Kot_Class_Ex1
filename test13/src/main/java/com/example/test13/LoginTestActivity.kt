package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityDetailBinding
import com.example.test13.databinding.ActivityLoginTestBinding

class LoginTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메인 액티비티에서 넘어온 값을 가지고 오는 부분
//        val data1 = intent.getStringExtra("data1")
//        val data2 = intent.getStringExtra("data2")
//        binding.detailId.text = "id: $data1"
//        binding.detailPass.text = "pass: $data2"

        binding.detailButton.setOnClickListener {
            val intent: Intent = Intent(this, MainTestActivity::class.java)
//            intent.putExtra("result","world")
            intent.putExtra("detailId",binding.detailId.text.toString())
            intent.putExtra("detailPass",binding.detailPass.text.toString())
            setResult(RESULT_OK, intent)

            finish()
            // 해당 액티비티를 끝내고 이전 액티비티로 이동함.
            // 실제 splash 화면을 구성할 때, 사용함.
            // 후처리를 뒤에서 다시 이야기 함
        }
    }
}