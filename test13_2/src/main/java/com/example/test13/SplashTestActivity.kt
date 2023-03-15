package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.test13_2.R

class SplashTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_test)

    Handler().postDelayed({
        val intent = Intent(this@SplashTestActivity,MainTestActivity::class.java)
        startActivity(intent)
        finish()
    },4000)
}
}