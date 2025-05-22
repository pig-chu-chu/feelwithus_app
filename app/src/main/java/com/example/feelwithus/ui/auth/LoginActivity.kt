package com.example.feelwithus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.feelwithus.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 初始化 Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // 綁定畫面上的元件
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        btnLogin = findViewById(R.id.btnlogin)
        txtSignup = findViewById(R.id.txt_signup)

        // 登入按鈕點擊
        btnLogin.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "請輸入帳號與密碼", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val Email = "$username@feelwithus.com"

            auth.signInWithEmailAndPassword(Email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "登入成功！", Toast.LENGTH_SHORT).show()
                        // 登入成功後可以跳轉到主畫面（MainActivity）
                        // startActivity(Intent(this, MainActivity::class.java))
                        // finish()
                    } else {
                        Toast.makeText(this, "登入失敗：" + task.exception?.message,
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        // 註冊連結點擊（跳轉到註冊畫面）
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
