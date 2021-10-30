package com.fitnest.domain.cookie


interface CookieStorageImpl {

    fun addCookie(cookie: Cookie)

    fun getCookies(): MutableList<Cookie>

}