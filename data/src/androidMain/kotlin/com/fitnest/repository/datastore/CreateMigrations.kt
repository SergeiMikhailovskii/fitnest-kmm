package com.fitnest.repository.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import org.kodein.di.DI
import org.kodein.di.instance

actual fun createMigrations(di: DI): List<DataMigration<Preferences>> {
    val context by di.instance<Context>()
    return listOf(SharedPreferencesMigration(context, "fitnestapp"))
}

actual fun producePath(di: DI): String {
    val context by di.instance<Context>()
    return context.filesDir.resolve(dataStoreFileName).absolutePath
}
