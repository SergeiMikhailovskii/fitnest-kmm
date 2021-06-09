package com.mikhailovskii.kmmtest

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}