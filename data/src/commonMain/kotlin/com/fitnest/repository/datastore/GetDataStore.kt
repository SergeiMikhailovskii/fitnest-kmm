package com.fitnest.repository.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal fun getDataStore(path: String, migrations: List<DataMigration<Preferences>>) =
    PreferenceDataStoreFactory.createWithPath(migrations = migrations) { path.toPath() }

internal const val dataStoreFileName = "fitnest.preferences_pb"
