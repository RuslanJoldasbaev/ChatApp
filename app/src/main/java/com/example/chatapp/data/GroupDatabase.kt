package com.example.chatapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Group::class], version = 1)
abstract class GroupDatabase: RoomDatabase() {
    companion object{
        private var instance:GroupDatabase? = null

        fun getInstance(context: Context): GroupDatabase{
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(context, GroupDatabase::class.java,"group.db")
                .allowMainThreadQueries()
                .build()

            instance = db
            return db
        }
    }

    abstract fun getGroupDao(): GroupDao
}