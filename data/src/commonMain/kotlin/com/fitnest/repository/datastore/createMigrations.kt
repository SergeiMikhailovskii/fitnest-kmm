package com.fitnest.repository.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.core.Preferences
import org.kodein.di.DI

expect fun createMigrations(di: DI): List<DataMigration<Preferences>>

expect fun producePath(di: DI): String
