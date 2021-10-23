package com.mikhailovskii.kmmtest.di

import com.mikhailovskii.kmmtest.repository.LocalStorageRepository
import com.mikhailovskii.kmmtest.service.Repository
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
    bind<Repository>() with singleton {
        Repository(di)
    }
    bind<LocalStorageRepository>() with singleton {
        LocalStorageRepository(di)
    }
}

val serviceModule = DI.Module("Service module") {
    bind<NetworkService>() with singleton {
        NetworkService(di)
    }
}