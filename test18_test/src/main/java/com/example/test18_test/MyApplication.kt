package com.example.test18_test

import android.app.Application
import com.example.test18_test.retrofit.NetworkService
import org.intellij.lang.annotations.Language
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
//        val QUERY = "travel"
        val Language = "KO"
        val API_KEY = "5f8322f624msh42723b9f41ad88cp1f27eejsnd752a89a3b21"
        val BASE_URL = "https://google-news-api1.p.rapidapi.com/search"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"


        //add....................................
        var networkService: NetworkService
        // NetworkService 객체 필요
        val retrofit: Retrofit
            // retrofit 객체 필요
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            // networkService 호출하게 되면
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}