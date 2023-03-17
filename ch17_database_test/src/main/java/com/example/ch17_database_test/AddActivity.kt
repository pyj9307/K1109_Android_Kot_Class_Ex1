package com.example.ch17_database_test

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ch17_database_test.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            val inputName = binding.nameEditView.text.toString()
            val inputAge = binding.ageEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL(
                // sql문에서 ? : 와일드카드 마스크 - 아래 arrayOf<String>(inputData)를 집어 넣는다
                "insert into USER (name, age) values (?, ?)",
                arrayOf<String>(inputName, inputAge)
            )
            db.close()

            // 현재 입력 액티비티, 메인으로 돌아갈 때 name, age 를 전달하는 부분
            val intent = intent
            intent.putExtra("name", inputName)
            intent.putExtra("age", inputAge)
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        else -> true
    }
}