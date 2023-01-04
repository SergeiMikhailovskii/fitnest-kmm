package com.fitnest.domain.di

import com.fitnest.domain.mapper.RegistrationResponseMapper
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val mapperModule = DI.Module("Mapper module") {
    bindProvider { RegistrationResponseMapper(instance()) }
}
