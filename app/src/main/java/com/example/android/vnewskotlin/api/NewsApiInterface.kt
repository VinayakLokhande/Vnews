package com.example.android.vnewskotlin.api

import com.example.android.vnewskotlin.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApiInterface {
    @GET
    fun getAllNews(@Url url : String) : Call<NewsModel>

    @GET
    fun getNewsByCategory(@Url url : String) : Call<NewsModel>
}