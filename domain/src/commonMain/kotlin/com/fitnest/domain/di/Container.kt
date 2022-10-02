package com.fitnest.domain.di

import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.auth.ForgetPasswordUseCase
import com.fitnest.domain.usecase.auth.GetLoginPageUseCase
import com.fitnest.domain.usecase.auth.LoginUserUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStepUseCase
import com.fitnest.domain.usecase.private_area.AddActivityUseCase
import com.fitnest.domain.usecase.private_area.DeactivateNotificationsUseCase
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.DeleteNotificationUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import com.fitnest.domain.usecase.private_area.PinNotificationUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
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
        GetLoginPageUseCase(instance(), instance(), instance())
    }
    bind<GenerateTokenUseCase>() with factory {
        GenerateTokenUseCase(instance(), instance())
    }
    bind<LoginPageValidationUseCase>() with factory {
        LoginPageValidationUseCase()
    }
    bind<LoginUserUseCase>() with factory {
        LoginUserUseCase(instance(), instance())
    }
    bind<ForgetPasswordUseCase>() with factory {
        ForgetPasswordUseCase(instance(), instance())
    }

    /**
     * Onboarding
     */
    bind<GetOnboardingStepUseCase>() with factory {
        GetOnboardingStepUseCase(instance(), instance(), instance())
    }
    bind<SubmitOnboardingStepUseCase>() with factory {
        SubmitOnboardingStepUseCase(instance(), instance())
    }

    /**
     * Registration
     */
    bind<GetRegistrationStepData>() with factory {
        GetRegistrationStepData(instance(), instance(), instance(), instance())
    }
    bind<SubmitRegistrationStepAndGetNextUseCase>() with factory {
        SubmitRegistrationStepAndGetNextUseCase(instance(), instance(), instance(), instance())
    }
    bind<CompleteAccountRegistrationValidationUseCase>() with factory {
        CompleteAccountRegistrationValidationUseCase()
    }
    bind<CreateAccountRegistrationValidationUseCase>() with factory {
        CreateAccountRegistrationValidationUseCase()
    }

    /**
     * Private area
     */
    bind<GetDashboardDataUseCase>() with factory {
        GetDashboardDataUseCase(instance(), instance(), instance())
    }
    bind<GetNotificationsPageUseCase>() with factory {
        GetNotificationsPageUseCase(instance(), instance(), instance())
    }
    bind<DeactivateNotificationsUseCase>() with factory {
        DeactivateNotificationsUseCase(instance(), instance())
    }
    bind<PinNotificationUseCase>() with factory {
        PinNotificationUseCase(instance(), instance())
    }
    bind<DeleteNotificationUseCase>() with factory {
        DeleteNotificationUseCase(instance(), instance())
    }
    bind<GetActivityTrackerPageUseCase>() with factory {
        GetActivityTrackerPageUseCase(instance(), instance(), instance())
    }
    bind<DeleteActivityUseCase>() with factory {
        DeleteActivityUseCase(instance(), instance(), instance())
    }
    bind<AddActivityUseCase>() with factory {
        AddActivityUseCase(instance(), instance(), instance())
    }
}

val mapperModule = DI.Module("Mapper module") {
    bind<RegistrationResponseMapper>() with factory {
        RegistrationResponseMapper(instance())
    }
}
