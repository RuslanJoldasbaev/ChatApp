package com.example.chatapp.presentation.group

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.models.Group
import com.example.chatapp.data.models.ResultData
import com.example.chatapp.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    val getGroupChatsFlow = MutableSharedFlow<List<Group>>()
    val errorFlow = MutableSharedFlow<Throwable>()
    val messageFlow = MutableSharedFlow<String>()
    val repo = MainRepository(FirebaseFirestore.getInstance(), FirebaseDatabase.getInstance())

    suspend fun getGroupChats() {
        repo.getGroupChatsFlow().onEach {
            when (it) {
                is ResultData.Success -> {
                    getGroupChatsFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun addGroup(name: String) {
        repo.addGroup(name)
    }

}