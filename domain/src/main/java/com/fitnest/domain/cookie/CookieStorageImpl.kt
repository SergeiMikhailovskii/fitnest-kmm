package com.fitnest.domain.cookie

import io.ktor.http.*

interface CookieStorageImpl {

    fun addCookie(cookie: Cookie)

    fun getCookies(): MutableList<Cookie>

}