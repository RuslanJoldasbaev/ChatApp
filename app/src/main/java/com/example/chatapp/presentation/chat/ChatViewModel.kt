package com.example.chatapp.presentation.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.chatapp.data.models.Group
import com.example.chatapp.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow

class ChatViewModel(private val repo:MainRepository) : ViewModel() {
    suspend fun sendMessage(message: String, groupId: String) {
        repo.sendMessage(message, groupId)
    }
}