package com.example.showmoview

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform