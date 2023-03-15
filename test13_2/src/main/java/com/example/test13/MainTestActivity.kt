package com.example.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test13.databinding.ActivityMain406Binding
import com.example.test13.databinding.ActivityMainBinding
import com.example.test13.databinding.ActivityMainTestBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, LoginTestActivity::class.java)
//            intent.putExtra("data1", "hello")
//            intent.putExtra("data2", 10)
            // 후처리 작업 하는 코드 부분.
            // startActivity(intent) 후처리 안할 때
            startActivityForResult(intent, 10)
        }
    }

    // 후처리하는 함수, 디테일 액티비티에서 넘어온 값을 처리하는 코드.
    // 자동으로 실행됨.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val binding = ActivityMainTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val detailId = data?.getStringExtra("detailId")
            val detailPass = data?.getStringExtra("detailPass")
            binding.detailId.text = "detailId : $detailId"
            binding.detailPass.text = "detailPass : $detailPass"
            Log.d("lsy","detailId : $detailId, detailPass : $detailPass")
            Toast.makeText(this@MainTestActivity,"detailId : $detailId, detailPass : $detailPass", Toast.LENGTH_SHORT).show()
        }
    }
}