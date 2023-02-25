package com.example.chatapp.data.models

data class MessageData(
    val message: String ,
    val user: String,
    val time: String = ""
)