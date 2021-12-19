package com.fitnest.domain.di

import com.fitnest.domain.usecase.*
import com.fitnest.domain.validator.CreateAccountRegistrationValidator
import org.kodein.di.*

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

val validatorModule = DI.Module("validator module") {
    bind<CreateAccountRegistrationValidator>() with multiton {
        CreateAccountRegistrationValidator()
    }
}