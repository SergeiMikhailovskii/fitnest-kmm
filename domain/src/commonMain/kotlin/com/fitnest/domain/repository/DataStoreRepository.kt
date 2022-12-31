package com.fitnest.domain.repository

interface DataStoreRepository {

    suspend fun saveCookie(key: String, value: String)

    suspend fun getCookie(key: String): String?

    suspend fun saveNotificationsEnabled(areNotificationsEnabled: Boolean)

    suspend fun getNotificationsEnabled(): Boolean
}
