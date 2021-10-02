package com.mikhailovskii.kmmtest.cookie

import io.ktor.http.*
import org.kodein.di.DI

actual class CookiesStorageImpl actual constructor(actual val di: DI) {
    actual fun addCookie(requestUrl: Url, cookie: Cookie) {
    }

    actual fun getCookies(): MutableList<Cookie> {
        TODO("Not yet implemented")
    }
}