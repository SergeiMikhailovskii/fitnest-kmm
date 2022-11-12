package com.fitnest.cookie

import com.fitnest.domain.cookie.CookieType
import com.fitnest.domain.repository.DataStoreRepository
import io.ktor.http.Cookie
import io.ktor.http.Url
import org.kodein.di.DI
import org.kodein.di.instance

class CookiesStorage(val di: DI) : io.ktor.client.plugins.cookies.CookiesStorage {

    private val localStorageRepository: DataStoreRepository by di.instance()

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        if (CookieType.values().any { it.value == cookie.name }) {
            localStorageRepository.saveString(cookie.name, cookie.value)
        }
    }

    override fun close() {}

    override suspend fun get(requestUrl: Url) = mutableListOf<Cookie>().apply {
        CookieType.values().forEach { cookie ->
            localStorageRepository.getString(cookie.value)?.let {
                println("Loaded: ${cookie.value}=$it")
                add(Cookie(cookie.value, it))
            }
        }
    }
}
