package com.fitnest.cookie

import com.fitnest.domain.cookie.CookieStorageImpl
import com.fitnest.domain.cookie.CookieType
import com.fitnest.domain.entity.base.Cookie
import com.fitnest.repository.LocalStorageRepository
import org.kodein.di.DI
import org.kodein.di.instance

actual class CookiesStorageImpl actual constructor(di: DI) : CookieStorageImpl {

    private val localStorageRepository: LocalStorageRepository by di.instance()

    actual override fun addCookie(cookie: Cookie) {
        println("Set cookie name=${cookie.name}, value=${cookie.value}")
        if (CookieType.values().any { it.value == cookie.name }) {
            localStorageRepository.saveValue(cookie.name, cookie.value)
        }
    }

    actual override fun getCookies() = mutableListOf<Cookie>().apply {
        CookieType.values().forEach { cookie ->
            localStorageRepository.getValue<String>(cookie.value, null)?.let {
                println("Loaded: ${cookie.value}=${it}")
                add(Cookie(cookie.value, it))
            }
        }
    }

}