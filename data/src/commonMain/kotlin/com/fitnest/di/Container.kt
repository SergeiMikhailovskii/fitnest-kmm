package com.fitnest.di

import com.fitnest.FitnestDatabase
import com.fitnest.cookie.CookiesStorage
import com.fitnest.db.SQLDelightDriverFactory
import com.fitnest.domain.entity.validator.EnumValidator
import com.fitnest.domain.entity.validator.MaxAgeValidator
import com.fitnest.domain.entity.validator.MaxValueValidator
import com.fitnest.domain.entity.validator.MinAgeValidator
import com.fitnest.domain.entity.validator.MinLengthValidator
import com.fitnest.domain.entity.validator.MinValueValidator
import com.fitnest.domain.entity.validator.RegExpValidator
import com.fitnest.domain.entity.validator.RequiredValidator
import com.fitnest.domain.entity.validator.Validator
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.exception.GeneralExceptionHandler
import com.fitnest.repository.DatabaseRepository
import com.fitnest.repository.NetworkRepository
import com.fitnest.repository.datastore.DataStoreRepository
import com.fitnest.repository.datastore.createMigrations
import com.fitnest.repository.datastore.getDataStore
import com.fitnest.repository.datastore.producePath
import com.fitnest.service.NetworkService
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataExceptionHandlerModule = DI.Module("Exception handler module") {
    bindSingleton<ExceptionHandler> { GeneralExceptionHandler() }
}

val databaseModule = DI.Module("Database module") {
    bindSingleton {
        val driver = SQLDelightDriverFactory(di).getDriver()
        FitnestDatabase(driver)
    }
}

val repositoryModule = DI.Module("Repository module") {
    bindSingleton<com.fitnest.domain.repository.DataStoreRepository> {
        DataStoreRepository(getDataStore(producePath(di), createMigrations(di)))
    }
    bindSingleton<com.fitnest.domain.repository.NetworkRepository> { NetworkRepository(instance()) }
    bindSingleton<com.fitnest.domain.repository.DatabaseRepository> { DatabaseRepository(instance()) }
}

val serviceModule = DI.Module("Service module") {
    bindSingleton { CookiesStorage(instance()) }
    bindSingleton<com.fitnest.domain.service.NetworkService> { NetworkService(instance()) }
    bindSingleton {
        val cookiesStorage = instance<CookiesStorage>()
        HttpClient {
            expectSuccess = true
            install(HttpCookies) {
                storage = cookiesStorage
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}

val serializationModule = DI.Module("Serialization module") {
    bindSingleton {
        val module = SerializersModule {
            polymorphic(Validator::class) {
                subclass(EnumValidator::class)
                subclass(MaxAgeValidator::class)
                subclass(MinValueValidator::class)
                subclass(MaxValueValidator::class)
                subclass(MinAgeValidator::class)
                subclass(MinLengthValidator::class)
                subclass(RegExpValidator::class)
                subclass(RequiredValidator::class)
            }
        }
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            serializersModule = module
            coerceInputValues = true
        }
    }
}
