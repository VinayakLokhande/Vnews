package com.example.android.vnewskotlin.model

data class NewsModel(
    val status : String,
    val totalResults : Int,
    val articles : List<ArticlesModel>
)

