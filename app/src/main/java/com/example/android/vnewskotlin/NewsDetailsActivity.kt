package com.example.android.vnewskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        // getting all intent data
        val title : String? = intent.getStringExtra("title")
        val des : String? = intent.getStringExtra("desc")
        val content : String? = intent.getStringExtra("content")
        val imgUrl : String? = intent.getStringExtra("imageUrl")
        val url : String? = intent.getStringExtra("url")


        Picasso.get().load(imgUrl).into(imgView)
        titleTxtView.text = title
        descTxtView.text = des
        contTxtView.text = content

        btnNews.setOnClickListener {
            val intent = Intent(this@NewsDetailsActivity,NewsContentOnWebView::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }

    }
}