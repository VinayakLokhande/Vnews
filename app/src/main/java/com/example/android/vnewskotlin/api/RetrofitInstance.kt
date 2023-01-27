package com.example.android.vnewskotlin.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var BaseURL : String = "https://newsapi.org/"

    val retrofitInstance : NewsApiInterface
    init {
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().build()
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofitInstance = retrofit.create(NewsApiInterface::class.java)
    }


}