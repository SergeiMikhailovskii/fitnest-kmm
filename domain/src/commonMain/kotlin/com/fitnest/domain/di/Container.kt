package com.fitnest.domain.di

import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.auth.ForgetPasswordUseCase
import com.fitnest.domain.usecase.auth.GetLoginPageUseCase
import com.fitnest.domain.usecase.auth.LoginUserUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStep
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStep
import com.fitnest.domain.usecase.private_area.*
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
import com.fitnest.domain.usecase.validation.LoginPageValidationUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val useCaseModule = DI.Module("use case module") {
    /**
     * Auth
     */
    bind<GetLoginPageUseCase>() with factory {
        GetLoginPageUseCase(instance(), instance())
    }
    bind<GenerateTokenUseCase>() with factory {
        GenerateTokenUseCase(instance())
    }
    bind<LoginPageValidationUseCase>() with factory {
        LoginPageValidationUseCase()
    }
    bind<LoginUserUseCase>() with factory {
        LoginUserUseCase(instance())
    }
    bind<ForgetPasswordUseCase>() with factory {
        ForgetPasswordUseCase(instance())
    }

    /**
     * Onboarding
     */
    bind<GetOnboardingStep>() with factory {
        GetOnboardingStep(instance())
    }
    bind<SubmitOnboardingStep>() with factory {
        SubmitOnboardingStep(instance())
    }

    /**
     * Registration
     */
    bind<GetRegistrationStepData>() with factory {
        GetRegistrationStepData(instance())
    }
    bind<SubmitRegistrationStepAndGetNext>() with factory {
        SubmitRegistrationStepAndGetNext(instance())
    }

    /**
     * Private area
     */
    bind<GetDashboardDataUseCase>() with factory {
        GetDashboardDataUseCase(instance(), instance())
    }
    bind<GetNotificationsPageUseCase>() with factory {
        GetNotificationsPageUseCase(instance(), instance())
    }
    bind<DeactivateNotificationsUseCase>() with factory {
        DeactivateNotificationsUseCase(instance())
    }
    bind<PinNotificationUseCase>() with factory {
        PinNotificationUseCase(instance())
    }
    bind<DeleteNotificationUseCase>() with factory {
        DeleteNotificationUseCase(instance())
    }
}
