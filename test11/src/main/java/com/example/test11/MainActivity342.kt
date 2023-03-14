package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain342Binding
import com.example.test11.databinding.Item342Binding

class MainActivity342 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 리사이클러뷰가 보여주게 될 레이아웃 - activit_main342.xml
        val binding = ActivityMain342Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임의로 보여줄 문자열 배열
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }

        // 만들었던 뷰홀더, 어댑터를 연결 시켜주는 부분.
        // 리사이클러 뷰 출력 하는 부분
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(datas)
        // 부가적인 옵션, 뒤에서 이미지 넣는 예제 확인해 볼 예정
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL)
        )
    }

    class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)

    // datas 매개변수에 목록에 넣을 데이터 배열이 왔음.
    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        // 갯수가 없으면 출력이 안됨. Int{return datas.size}
        override fun getItemCount(): Int{
            Log.d("kkang", "init datas size: ${datas.size}")
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("kkang","onBindViewHolder : $position")
            val binding=(holder as MyViewHolder).binding
            //뷰에 데이터 출력 item_342 에서 지정한 id item_data 에 text 데이터 추가.
            binding.itemData.text= datas[position]

            //뷰에 이벤트 추가 item_342 에서 지정한 id item_root 에 setOnClickListener 이벤트 추가.
            // 해당 항목 하나 클릭 시 동작하는 부분.
            binding.itemRoot.setOnClickListener{
                Log.d("kkang", "item root click : ${position+1}")
            }
        }
    }
}

