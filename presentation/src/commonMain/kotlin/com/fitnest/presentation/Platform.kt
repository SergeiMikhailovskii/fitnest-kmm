package com.fitnest.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform