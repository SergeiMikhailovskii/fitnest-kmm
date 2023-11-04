package com.fitnest.repository.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.core.Preferences
import org.kodein.di.DI

actual fun producePath(di: DI) = ""

actual fun createMigrations(di: DI) = listOf<DataMigration<Preferences>>()
