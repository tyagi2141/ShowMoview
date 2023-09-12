package com.example.showmoview.util

import com.example.showmoview.di.getShareModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getShareModule())
    }
}