package com.example.test17_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test17_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var filePath: String
    // 이메일 문자열을 담는 배열
    var datas: MutableList<String>? = null
    // 패스워드 문자열을 담는 배열
    var datas2: MutableList<String>? = null
    // MyAdapter 클래스명으로 만들었음. 리사이클러뷰 사용하기 위해서 필요한 재료
    lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 현재 메인에서 -> 입력 액티비티에서 이름, 나이 입력하고 그 값을 처리하는 부분
        // 입력 액티비티에서 finish() 호출해서 메인 액티비티로 넘어 왔고 인텐트에 담은 데이터를 처리함.
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 이름을 처리하는 부분
            it.data!!.getStringExtra("email")?.let {
                // name의 키에 해당하는 값 -> it
                datas?.add(it)
                // 어댑터에 연결된 datas 배열의 값을 업데이트
                adapter.notifyDataSetChanged()
            }
            // 나이을 처리하는 부분
            it.data!!.getStringExtra("pass")?.let {
                datas2?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        binding.mainFab.setOnClickListener {
            // 간단히 다른 액티비티 화면으로 이동할 때.
            // 지금은 후처리가 다 들어가 있음
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas= mutableListOf<String>()
        // 이거 datas2 없어서 MyAdapter에서 에러 남...
        datas2= mutableListOf<String>()

        //add......................

        // 디비 select
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from User", null)
        cursor.run {
            while(moveToNext()){
                // 첫 컬럼에 이름 값을 읽어서 배열에 담기
                datas?.add(cursor.getString(1))
                // 두번째 컬럼에 나이 값을 읽어서 배열에 담기
                datas2?.add(cursor.getString(2))
            }
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        // 리사이클러 뷰에서
        binding.mainRecyclerView.layoutManager=layoutManager
        // MyAdapter에 datas 값을 넣어주고
        adapter=MyAdapter(datas, datas2)
        // 메인에 리사이클러뷰 어댑터와 레이아웃 매니저를 연결하는 부분.
        binding.mainRecyclerView.adapter=adapter
        // 목록의 요소를 꾸며주는 옵션 부분
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}