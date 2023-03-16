package com.example.chatapp.di

import com.example.chatapp.domain.MainRepository
import com.example.chatapp.presentation.chat.ChatViewModel
import com.example.chatapp.presentation.group.AddGroupViewModel
import com.example.chatapp.presentation.group.GroupViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val appModule = module {
    single<MainRepository> {
        MainRepository(fb = get(), rd = get())
    }

    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }

    single<FirebaseDatabase> {
        FirebaseDatabase.getInstance()
    }
}

val viewModelModule = module {
    single<ChatViewModel> {
        ChatViewModel(repo = get())
    }

    single<AddGroupViewModel> {
        AddGroupViewModel(repo = get())
    }

    single<GroupViewModel> {
        GroupViewModel(repo = get())
    }
}