package com.mikhailovskii.kmmtest.di

import com.mikhailovskii.kmmtest.repository.LocalStorageRepository
import com.mikhailovskii.kmmtest.cookie.CookiesStorageImpl
import com.mikhailovskii.kmmtest.repository.NetworkRepository
import com.mikhailovskii.kmmtest.service.NetworkService
import com.mikhailovskii.kmmtest.usecase.LoginUseCase
import com.mikhailovskii.kmmtest.usecase.GenerateTokenUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val useCaseModule = DI.Module("use case module") {
    bind<LoginUseCase>() with singleton {
        LoginUseCase(di)
    }
    bind<GenerateTokenUseCase>() with singleton {
        GenerateTokenUseCase(di)
    }
}

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