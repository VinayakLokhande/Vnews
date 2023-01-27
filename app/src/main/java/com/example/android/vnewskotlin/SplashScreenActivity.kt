package com.example.android.vnewskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {

            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            this@SplashScreenActivity.finish()

        },4000)

    }
}