package com.example.test17_test

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.test17_test.databinding.ActivityLoginBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            val inputEmail = binding.emailEditView.text.toString()
            val inputPass = binding.passEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL(
                // sql문에서 ? : 와일드카드 마스크 - 아래 arrayOf<String>(inputData)를 집어 넣는다
                "insert into USER (name, age) values (?, ?)",
                arrayOf<String>(inputEmail, inputPass)
            )
            db.close()

            // 현재 입력 액티비티, 메인으로 돌아갈 때 name, age 를 전달하는 부분
            val intent = intent
            intent.putExtra("email", inputEmail)
            intent.putExtra("pass", inputPass)
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        else -> true
    }
}