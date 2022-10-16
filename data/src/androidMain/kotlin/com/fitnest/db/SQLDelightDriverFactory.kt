package com.fitnest.db

import android.content.Context
import com.fitnest.FitnestDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.DI
import org.kodein.di.instance

actual class SQLDelightDriverFactory actual constructor(val di: DI) {
    private val context: Context by di.instance()

    actual fun getDriver(): SqlDriver =
        AndroidSqliteDriver(FitnestDatabase.Schema, context, "FitnestDatabase.db")
}
