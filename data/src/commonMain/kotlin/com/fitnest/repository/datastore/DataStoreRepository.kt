package com.fitnest.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepository internal constructor(
    private val dataStore: DataStore<Preferences>
) : com.fitnest.domain.repository.DataStoreRepository {

    override suspend fun saveString(key: String, value: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = value }
    }

    override suspend fun getString(key: String) = dataStore.data.map {
        it[stringPreferencesKey(key)]
    }.first()
}
