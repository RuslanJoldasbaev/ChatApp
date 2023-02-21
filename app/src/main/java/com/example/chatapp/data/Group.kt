package com.example.chatapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Group(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "group") val group: String,
    @ColumnInfo(name = "message") val message : String,
    @ColumnInfo(name = "date") val date: String,

)
