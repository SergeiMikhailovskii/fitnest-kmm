package com.mikhailovskii.kmmtest.cookie

import com.fitnest.domain.cookie.CookieStorageImpl
import io.ktor.http.*
import org.kodein.di.DI

actual class CookiesStorageImpl actual constructor(actual val di: DI) : CookieStorageImpl {
    actual override fun addCookie(cookie: Cookie) {
    }

    actual override fun getCookies(): MutableList<Cookie> {
        TODO("Not yet implemented")
    }
}