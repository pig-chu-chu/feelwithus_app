package com.example.feelwithus.data.model

data class User(
    val uid: String = "",         // 使用者唯一 ID（Firebase Authentication會給）
    val username: String = "",    // 使用者名稱（帳號）
    val createdAt: Long = 0L      // 帳號建立時間
)
