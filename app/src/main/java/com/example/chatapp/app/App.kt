package com.example.chatapp.app

import android.app.Application
import com.example.chatapp.di.appModule
import com.example.chatapp.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            modules(listOf(appModule, viewModelModule))
        }
    }
}