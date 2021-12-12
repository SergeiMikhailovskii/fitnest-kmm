package com.fitnest.domain.di

import com.fitnest.domain.usecase.*
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
    bind<GetRegistrationStepData>() with singleton {
        GetRegistrationStepData(instance())
    }
    bind<SubmitOnboardingStep>() with singleton {
        SubmitOnboardingStep(instance())
    }
}