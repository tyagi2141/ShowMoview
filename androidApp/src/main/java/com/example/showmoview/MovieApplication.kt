package com.example.showmoview

import android.app.Application
import com.example.showmoview.di.appModule
import com.example.showmoview.di.getShareModule
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getShareModule())
        }
    }
}