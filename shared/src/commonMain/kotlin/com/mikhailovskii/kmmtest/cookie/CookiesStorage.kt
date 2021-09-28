package com.mikhailovskii.kmmtest.cookie

import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.*
import org.kodein.di.DI

class CookiesStorage(val di: DI) : CookiesStorage {

    private val cookiesStorageImpl = CookiesStorageImpl(di)

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        cookiesStorageImpl.addCookie(requestUrl, cookie)
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url) = cookiesStorageImpl.getCookies()
}

expect class CookiesStorageImpl(di: DI) {
    val di: DI
    fun addCookie(requestUrl: Url, cookie: Cookie)
    fun getCookies(): MutableList<Cookie>
}