package com.example.feelwithus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.feelwithus.R

class StartActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var txtSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // 綁定元件
        btnLogin = findViewById(R.id.btnLogin)   // 假設你的按鈕叫 btnLogin
        txtSignup = findViewById(R.id.txt_signup) // 假設你的註冊文字叫 txt_signup

        // 點擊登入按鈕
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // 點擊註冊文字
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
