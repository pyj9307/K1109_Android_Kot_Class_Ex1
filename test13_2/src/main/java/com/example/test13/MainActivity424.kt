package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_2.databinding.ActivityMain424Binding

class MainActivity424 : AppCompatActivity() {
    var count = 0
    var onCreateCount = 0
    var onRestoreCount = 0
    var onSaveCount = 0
    // lateinit 초기화를 뒤늦게 해도 됨.
    lateinit var binding: ActivityMain424Binding

    // 최초에 한번 호출이 됩니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMain424Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.plusCountButton.setOnClickListener {
            count++
            binding.countResultView.text="$count"
        }

        Log.d("lsy","onCreate 호출 부분. onCreateCount 횟수 : $onCreateCount")
        onCreateCount++
    }

    override fun onStart() {
        super.onStart()
        Log.d("lsy","onStart 호출 부분.")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lsy","onStart 호출 부분.")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lsy","onPause 호출 부분.")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lsy","onStop 호출 부분.")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")

        binding.countResultView.text="$data1 - $data2"
        Log.d("lsy","onRestoreInstanceState 호출 부분. onRestoreCount 횟수 : $onRestoreCount")
        onRestoreCount++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("kkang","onSaveInstanceState..........")
        outState.putString("data1", "hello")
        outState.putInt("data2", 10)
        Log.d("lsy","onSaveInstanceState 호출 부분. onSaveCount 횟수 : $onSaveCount")
        onSaveCount++
    }
}