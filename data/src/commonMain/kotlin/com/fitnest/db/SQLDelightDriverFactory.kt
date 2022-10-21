package com.fitnest.db

import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.DI

expect class SQLDelightDriverFactory(di: DI) {
    fun getDriver(): SqlDriver
}
