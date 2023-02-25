package com.example.chatapp.presentation.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.chatapp.data.models.Group
import com.example.chatapp.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    val repo = MainRepository(FirebaseFirestore.getInstance(), FirebaseDatabase.getInstance())

    suspend fun sendMessage(message: String, groupId: String) {
        repo.sendMessage(message, groupId)
    }
}