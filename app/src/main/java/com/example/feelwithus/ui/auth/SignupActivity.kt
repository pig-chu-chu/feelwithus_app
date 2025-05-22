package com.example.feelwithus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.feelwithus.R
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var usernameSignup: EditText
    private lateinit var passwordSignup: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // 初始化 Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // 綁定 XML 的元件
        usernameSignup = findViewById(R.id.username_signup)
        passwordSignup = findViewById(R.id.password_signup)
        btnRegister = findViewById(R.id.btn_register)

        // 註冊按鈕點擊事件
        btnRegister.setOnClickListener {
            val username = usernameSignup.text.toString().trim()
            val password = passwordSignup.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "請填寫帳號與密碼", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "密碼至少6個字元", Toast.LENGTH_SHORT).show()
                // Firebase 最低要求
                return@setOnClickListener
            }

            // username+@feelwithus.com  裝成email儲存至Firebase
            val Email = "$username@feelwithus.com"

            // 用 Firebase Authentication 建立帳號
            auth.createUserWithEmailAndPassword(Email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "註冊成功！請登入", Toast.LENGTH_SHORT).show()
                        // 註冊成功，跳回登入畫面
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "註冊失敗：" + task.exception?.message,
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
