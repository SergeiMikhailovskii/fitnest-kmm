package com.fitnest.di

import com.fitnest.cookie.CookiesStorageImpl
import com.fitnest.mapper.RegistrationResponseMapper
import com.fitnest.repository.LocalStorageRepository
import com.fitnest.repository.NetworkRepository
import com.fitnest.service.NetworkService
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
        RegistrationResponseMapper()
    }
}