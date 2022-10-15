package com.fitnest.db

import com.fitnest.FitnestDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.kodein.di.DI

actual class SQLDelightDriverFactory actual constructor(di: DI) {
    actual fun getDriver(): SqlDriver =
        NativeSqliteDriver(FitnestDatabase.Schema, "FitnestDatabase.db")
}