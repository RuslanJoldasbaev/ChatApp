package com.example.chatapp.presentation.group

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddGroupViewModel(application: Application) : AndroidViewModel(application) {
    val repo = MainRepository(FirebaseFirestore.getInstance(), FirebaseDatabase.getInstance())

    val addGroupSuccesFlow = MutableSharedFlow<String>()
    suspend fun addGroup(name: String) {
        repo.addGroup(name).onEach {
            repo.andGroupToRealtimeDatabase(it)
            addGroupSuccesFlow.emit(it)
        }.launchIn(viewModelScope)
    }

}