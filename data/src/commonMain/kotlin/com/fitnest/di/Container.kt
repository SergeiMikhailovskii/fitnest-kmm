package com.fitnest.di

import com.fitnest.FitnestDatabase
import com.fitnest.cookie.CookiesStorageImpl
import com.fitnest.db.SQLDelightDriverFactory
import com.fitnest.domain.entity.validator.EnumValidator
import com.fitnest.domain.entity.validator.MaxAgeValidator
import com.fitnest.domain.entity.validator.MaxValueValidator
import com.fitnest.domain.entity.validator.MinAgeValidator
import com.fitnest.domain.entity.validator.MinLengthValidator
import com.fitnest.domain.entity.validator.RegExpValidator
import com.fitnest.domain.entity.validator.RequiredValidator
import com.fitnest.domain.entity.validator.Validator
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.exception.GeneralExceptionHandler
import com.fitnest.repository.LocalStorageRepository
import com.fitnest.repository.NetworkRepository
import com.fitnest.service.NetworkService
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataExceptionHandlerModule = DI.Module("Exception handler module") {
    bindSingleton<ExceptionHandler> {
        GeneralExceptionHandler()
    }
}

val databaseModule = DI.Module("Database module") {
    bindSingleton {
        val driver = SQLDelightDriverFactory(di).getDriver()
        FitnestDatabase(driver)
    }
}

val repositoryModule = DI.Module("Repository module") {
    bindSingleton<com.fitnest.domain.repository.NetworkRepository> {
        NetworkRepository(instance())
    }
    bindSingleton {
        LocalStorageRepository(di)
    }
}

val serviceModule = DI.Module("Service module") {
    bindSingleton<com.fitnest.domain.service.NetworkService> {
        NetworkService(di)
    }
}

val cookieModule = DI.Module("Cookie module") {
    bindSingleton<com.fitnest.domain.cookie.CookieStorageImpl> {
        CookiesStorageImpl(di)
    }
}

val serializationModule = DI.Module("Serialization module") {
    bindSingleton {
        val module = SerializersModule {
            polymorphic(Validator::class) {
                subclass(EnumValidator::class)
                subclass(MaxAgeValidator::class)
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
