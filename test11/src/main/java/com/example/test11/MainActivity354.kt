package com.example.test11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain354Binding
import com.example.test11.databinding.Item354Binding

class MainActivity354 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main354)

        // 바인딩 작업, activity_main_354의 viewpager2 가 기본 화면, 여기에 데이터 항목들이 주입됨.
        val binding= ActivityMain354Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 부분은 파이어베이스에서 데이터를 받아오게 됨.
        // 지금은 임시 데이터
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        binding.viewpager.adapter=MyPagerAdapter(datas)
    }

    // 뷰홀더 : 뷰 객체들을 모아주는 역할.
    // 주 생성자의 매개변수에 val binding: Item354Binding 가 들어가면서, 코드가 깔끔해지고, 작업이 편해짐.
    class MyPagerViewHolder(val binding: Item354Binding): RecyclerView.ViewHolder(binding.root)

    // 어댑터 : 뷰 객체에 데이터를 연결(바인딩)하는 작업.
    // 주생성자의 매개변수 부분이 임의로 만든 리스트, 사실은 여기에 외부에서 데이터를 받아 올 예정.
    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int{
            return datas.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyPagerViewHolder(Item354Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // MyPagerViewHolder의 뷰가 Item354Binding
            // Item354Binding -> item_354 파일 안에 정의된 id -> item_pager_textView -> itemPagerTextView
            val binding=(holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text=datas[position]
            when(position % 3){
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}