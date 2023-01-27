package com.example.android.vnewskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_news_content_on_web_view.*

class NewsContentOnWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content_on_web_view)

        val url : String? = intent.getStringExtra("url")


        // setting web view
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url!!)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }

    }
}