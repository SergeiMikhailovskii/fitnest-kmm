package com.fitnest.domain.di

import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.LoginUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStep
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStep
import com.fitnest.domain.usecase.private_area.DeactivateNotificationsUseCase
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import com.fitnest.domain.usecase.private_area.PinNotificationUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
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
}
