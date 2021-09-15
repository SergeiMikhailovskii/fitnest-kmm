package com.mikhailovskii.kmmtest.cookie

import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.*

class CookiesStorage : CookiesStorage {

    private val cookieMap = mutableMapOf<String, String>()

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        println("Set cookie name=${cookie.name}, value=${cookie.value}")
        cookieMap[cookie.name] = cookie.value
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url) = mutableListOf<Cookie>().apply {
        cookieMap.forEach {
            this.add(Cookie(it.key, it.value))
        }

    }
}