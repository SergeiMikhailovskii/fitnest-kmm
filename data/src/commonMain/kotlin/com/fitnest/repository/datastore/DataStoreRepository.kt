package com.fitnest.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepository internal constructor(
    private val dataStore: DataStore<Preferences>
) : com.fitnest.domain.repository.DataStoreRepository {

    override suspend fun saveCookie(key: String, value: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = value }
    }

    override suspend fun getCookie(key: String) = dataStore.data.map {
        it[stringPreferencesKey(key)]
    }.first()

    override suspend fun saveNotificationsEnabled(areNotificationsEnabled: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(ARE_NOTIFICATIONS_ENABLED_KEY)] = areNotificationsEnabled
        }
    }

    override suspend fun getNotificationsEnabled() = dataStore.data.map {
        it[booleanPreferencesKey(ARE_NOTIFICATIONS_ENABLED_KEY)]
    }.first() ?: false

    private companion object {
        const val ARE_NOTIFICATIONS_ENABLED_KEY = "areNotificationsEnabled"
    }
}
