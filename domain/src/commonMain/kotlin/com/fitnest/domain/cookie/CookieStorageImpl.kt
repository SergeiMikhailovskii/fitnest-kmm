package com.fitnest.domain.cookie

import com.fitnest.domain.entity.base.Cookie

interface CookieStorageImpl {

    fun addCookie(cookie: Cookie)

    fun getCookies(): MutableList<Cookie>
}
