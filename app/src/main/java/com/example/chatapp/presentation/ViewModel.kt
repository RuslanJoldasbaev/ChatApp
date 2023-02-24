package com.example.chatapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.chatapp.data.Group
import kotlinx.coroutines.flow.MutableSharedFlow

class ViewModel(application: Application): AndroidViewModel(application) {



    val getAllGroupFlow = MutableSharedFlow<List<Group>>()
    val messageFlow = MutableSharedFlow<String>()

}