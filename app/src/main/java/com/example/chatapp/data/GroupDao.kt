package com.example.chatapp.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GroupDao {
    @Query("SELECT * FROM Group")
    suspend fun getAllGroup() :List<Group>


}