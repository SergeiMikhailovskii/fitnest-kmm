package com.fitnest.cookie

import com.fitnest.domain.cookie.CookieStorageImpl
import com.fitnest.domain.entity.base.Cookie
import io.ktor.http.Url
import org.kodein.di.DI
import org.kodein.di.instance

class CookiesStorage(val di: DI) : io.ktor.client.plugins.cookies.CookiesStorage {

    private val cookiesStorageImpl: CookieStorageImpl by di.instance()

    override suspend fun addCookie(requestUrl: Url, cookie: io.ktor.http.Cookie) {
        val localCookie = Cookie(cookie.name, cookie.value)
        cookiesStorageImpl.addCookie(localCookie)
    }

    override fun close() {
    }

    override suspend fun get(requestUrl: Url) =
        cookiesStorageImpl.getCookies().map {
            io.ktor.http.Cookie(name = it.name, value = it.value)
        }
}

expect class CookiesStorageImpl(di: DI) : CookieStorageImpl {
    override fun addCookie(cookie: Cookie)
    override fun getCookies(): MutableList<Cookie>
}
