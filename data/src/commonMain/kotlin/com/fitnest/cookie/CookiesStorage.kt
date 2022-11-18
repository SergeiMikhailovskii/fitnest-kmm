package com.fitnest.cookie

import com.fitnest.domain.cookie.CookieType
import com.fitnest.domain.repository.DataStoreRepository
import io.github.aakira.napier.Napier
import io.ktor.http.Cookie
import io.ktor.http.Url

class CookiesStorage(private val localStorageRepository: DataStoreRepository) :
    io.ktor.client.plugins.cookies.CookiesStorage {

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        if (CookieType.values().any { it.value == cookie.name }) {
            localStorageRepository.saveString(cookie.name, cookie.value)
        }
    }

    override fun close() {}

    override suspend fun get(requestUrl: Url) = mutableListOf<Cookie>().apply {
        CookieType.values().forEach { cookie ->
            localStorageRepository.getString(cookie.value)?.let {
                Napier.i("Loaded: ${cookie.value}=$it")
                add(Cookie(cookie.value, it))
            }
        }
    }
}
