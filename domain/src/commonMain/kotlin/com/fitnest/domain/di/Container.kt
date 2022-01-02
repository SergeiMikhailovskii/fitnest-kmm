package com.fitnest.domain.di

import com.fitnest.domain.usecase.*
import com.fitnest.domain.validator.CreateAccountRegistrationValidator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val useCaseModule = DI.Module("use case module") {
    bind<LoginUseCase>() with factory {
        LoginUseCase(di)
    }
    bind<GenerateTokenUseCase>() with factory {
        GenerateTokenUseCase(instance())
    }
    bind<GetOnboardingStep>() with factory {
        GetOnboardingStep(instance())
    }
    bind<GetRegistrationStepData>() with factory {
        GetRegistrationStepData(instance())
    }
    bind<SubmitOnboardingStep>() with factory {
        SubmitOnboardingStep(instance())
    }
    bind<SubmitRegistrationStep>() with factory {
        SubmitRegistrationStep(instance())
    }
}

val validatorModule = DI.Module("validator module") {
    bind<CreateAccountRegistrationValidator>() with factory {
        CreateAccountRegistrationValidator()
    }
}