package com.mikhailovskii.kmmtest.cookie

import com.fitnest.domain.cookie.CookieStorageImpl
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.*
import org.kodein.di.DI
import org.kodein.di.instance

class CookiesStorage(val di: DI) : CookiesStorage {

    private val cookiesStorageImpl: CookieStorageImpl by di.instance()

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        val localCookie = com.fitnest.domain.cookie.Cookie(cookie.name, cookie.value)
        cookiesStorageImpl.addCookie(localCookie)
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url) =
        cookiesStorageImpl.getCookies().map { Cookie(name = it.name, value = it.value) }
}

expect class CookiesStorageImpl(di: DI) : CookieStorageImpl {
    override fun addCookie(cookie: com.fitnest.domain.cookie.Cookie)
    override fun getCookies(): MutableList<com.fitnest.domain.cookie.Cookie>
}