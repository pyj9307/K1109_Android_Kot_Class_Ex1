package com.example.test11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test11.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    // activity_main337 에 name으로 지정된 위치를 불러옴
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        // onCreateView 결과는 뷰를 반환한다.
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }
}