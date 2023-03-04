package com.fitnest.util

import com.fitnest.FitnestDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

internal actual fun testDriver(): SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
    FitnestDatabase.Schema.create(this)
}
