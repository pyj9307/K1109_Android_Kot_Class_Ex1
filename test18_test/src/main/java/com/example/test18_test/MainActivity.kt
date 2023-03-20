package com.example.test18_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test18_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "retrofit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitFragment= RetrofitFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, retrofitFragment)
            .commit()
//        supportActionBar?.title="Volley Test"
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId === R.id.menu_volley && mode !== "volley"){
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.activity_content)
//                .commit()
//            mode="volley"
//            supportActionBar?.title="Volley Test"
//        }else if(item.itemId === R.id.menu_retrofit && mode !== "retrofit"){
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.activity_content, retrofitFragment)
//                .commit()
//            mode="retrofit"
//            supportActionBar?.title="Retrofit Test"
//        }
//        return super.onOptionsItemSelected(item)
//    }
}