package com.mikhailovskii.kmmtest.cookie

import com.mikhailovskii.kmmtest.enum.CookieType
import com.mikhailovskii.kmmtest.repository.LocalStorageRepository
import io.ktor.http.*
import org.kodein.di.DI
import org.kodein.di.instance

actual class CookiesStorageImpl actual constructor(actual val di: DI) {

    private val localStorageRepository: LocalStorageRepository by di.instance()

    actual fun addCookie(cookie: Cookie) {
        println("Set cookie name=${cookie.name}, value=${cookie.value}")
        if (CookieType.values().any { it.value == cookie.name }) {
            localStorageRepository.saveValue(cookie.name, cookie.value)
        }
    }

    actual fun getCookies() = mutableListOf<Cookie>().apply {
        CookieType.values().forEach { cookie ->
            localStorageRepository.getValue<String>(cookie.value, null)?.let {
                println("Loaded: ${cookie.value}=${it}")
                add(Cookie(cookie.value, it))
            }
        }
    }

}