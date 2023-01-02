package com.fitnest.domain.di

import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val useCaseModule = DI.Module("use case module") {
    bindProvider { ClearCacheUseCase(instance()) }
}

val mapperModule = DI.Module("Mapper module") {
    bindProvider { RegistrationResponseMapper(instance()) }
}
