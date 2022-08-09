package com.fitnest.di

import com.fitnest.cookie.CookiesStorageImpl
import com.fitnest.domain.entity.validator.*
import com.fitnest.mapper.RegistrationResponseMapper
import com.fitnest.repository.LocalStorageRepository
import com.fitnest.repository.NetworkRepository
import com.fitnest.service.NetworkService
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.kodein.di.*

val repositoryModule = DI.Module("Repository module") {
    bind<com.fitnest.domain.repository.NetworkRepository>() with singleton {
        NetworkRepository(instance(), instance())
    }
    bind<LocalStorageRepository>() with singleton {
        LocalStorageRepository(di)
    }
}

val serviceModule = DI.Module("Service module") {
    bind<com.fitnest.domain.service.NetworkService>() with singleton {
        NetworkService(di)
    }
}

val cookieModule = DI.Module("Cookie module") {
    bind<com.fitnest.domain.cookie.CookieStorageImpl>() with singleton {
        CookiesStorageImpl(di)
    }
}

val mapperModule = DI.Module("Mapper module") {
    bind<RegistrationResponseMapper>() with factory {
        RegistrationResponseMapper(instance())
    }
}

val serializationModule = DI.Module("Serialization module") {
    bind<Json>() with singleton {
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
