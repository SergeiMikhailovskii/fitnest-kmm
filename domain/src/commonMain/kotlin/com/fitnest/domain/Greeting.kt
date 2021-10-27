package com.fitnest.domain

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}