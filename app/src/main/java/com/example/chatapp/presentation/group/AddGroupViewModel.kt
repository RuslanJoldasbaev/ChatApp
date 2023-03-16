package com.example.chatapp.presentation.group

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddGroupViewModel(private val repo: MainRepository) : ViewModel() {

    val addGroupSuccesFlow = MutableSharedFlow<String>()
    suspend fun addGroup(name: String) {
        repo.addGroup(name).onEach {
            repo.andGroupToRealtimeDatabase(it)
            addGroupSuccesFlow.emit(it)
        }.launchIn(viewModelScope)
    }

}