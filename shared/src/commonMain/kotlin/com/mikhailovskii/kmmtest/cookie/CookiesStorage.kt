package com.mikhailovskii.kmmtest.cookie

import com.fitnest.domain.cookie.CookieStorageImpl
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.*
import org.kodein.di.DI

class CookiesStorage(val di: DI) : CookiesStorage {

    private val cookiesStorageImpl = CookiesStorageImpl(di)

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        cookiesStorageImpl.addCookie(cookie)
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url) = cookiesStorageImpl.getCookies()
}

expect class CookiesStorageImpl(di: DI) : CookieStorageImpl {
    val di: DI
    override fun addCookie(cookie: Cookie)
    override fun getCookies(): MutableList<Cookie>
}