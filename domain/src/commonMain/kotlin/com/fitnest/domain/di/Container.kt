package com.fitnest.domain.di

import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.GetOnboardingStep
import com.fitnest.domain.usecase.LoginUseCase
import com.fitnest.domain.usecase.SubmitOnboardingStep
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val useCaseModule = DI.Module("use case module") {
    bind<LoginUseCase>() with singleton {
        LoginUseCase(di)
    }
    bind<GenerateTokenUseCase>() with singleton {
        GenerateTokenUseCase(instance())
    }
    bind<GetOnboardingStep>() with singleton {
        GetOnboardingStep(instance())
    }
    bind<SubmitOnboardingStep>() with singleton {
        SubmitOnboardingStep(instance())
    }
}