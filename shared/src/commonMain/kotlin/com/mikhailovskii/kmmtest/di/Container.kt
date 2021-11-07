package com.mikhailovskii.kmmtest.di

import com.mikhailovskii.kmmtest.repository.LocalStorageRepository
import com.mikhailovskii.kmmtest.cookie.CookiesStorageImpl
import com.mikhailovskii.kmmtest.repository.NetworkRepository
import com.mikhailovskii.kmmtest.service.NetworkService
import com.fitnest.domain.usecase.LoginUseCase
import com.fitnest.domain.usecase.GenerateTokenUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val repositoryModule = DI.Module("Repository module") {
    bind<com.fitnest.domain.repository.NetworkRepository>() with singleton {
        NetworkRepository(di)
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