package com.fitnest.domain.di

import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerCacheToResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapper
import com.fitnest.domain.mapper.db.DashboardCacheToResponseMapper
import com.fitnest.domain.mapper.db.DashboardResponseToCacheMapper
import com.fitnest.domain.mapper.db.ProfileCacheToResponseMapper
import com.fitnest.domain.mapper.db.ProfileResponseToCacheMapper
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.auth.ForgetPasswordUseCase
import com.fitnest.domain.usecase.auth.GetLoginPageUseCase
import com.fitnest.domain.usecase.auth.LoginUserUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStepUseCase
import com.fitnest.domain.usecase.private_area.AddActivityUseCase
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import com.fitnest.domain.usecase.private_area.DeactivateNotificationsUseCase
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.DeleteNotificationUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import com.fitnest.domain.usecase.private_area.GetProfilePageUseCase
import com.fitnest.domain.usecase.private_area.PinNotificationUseCase
import com.fitnest.domain.usecase.private_area.SetNotificationsEnabledUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
import com.fitnest.domain.usecase.validation.LoginPageValidationUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val useCaseModule = DI.Module("use case module") {
    /**
     * Auth
     */
    bindProvider { GetLoginPageUseCase(instance(), instance(), instance()) }
    bindProvider { GenerateTokenUseCase(instance(), instance()) }
    bindProvider { LoginPageValidationUseCase() }
    bindProvider { LoginUserUseCase(instance(), instance()) }
    bindProvider { ForgetPasswordUseCase(instance(), instance()) }

    /**
     * Onboarding
     */
    bindProvider { GetOnboardingStepUseCase(instance(), instance(), instance()) }
    bindProvider { SubmitOnboardingStepUseCase(instance(), instance()) }

    /**
     * Registration
     */
    bindProvider { GetRegistrationStepData(instance(), instance(), instance(), instance()) }
    bindProvider {
        SubmitRegistrationStepAndGetNextUseCase(instance(), instance(), instance(), instance())
    }
    bindProvider { CompleteAccountRegistrationValidationUseCase() }
    bindProvider { CreateAccountRegistrationValidationUseCase() }

    /**
     * Private area
     */
    bindProvider {
        GetDashboardDataUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider { GetNotificationsPageUseCase(instance(), instance(), instance()) }
    bindProvider { DeactivateNotificationsUseCase(instance(), instance()) }
    bindProvider { PinNotificationUseCase(instance(), instance()) }
    bindProvider { DeleteNotificationUseCase(instance(), instance()) }
    bindProvider {
        GetActivityTrackerPageUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider {
        DeleteActivityUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider { AddActivityUseCase(instance(), instance(), instance(), instance(), instance()) }
    bindProvider {
        GetProfilePageUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider { ClearCacheUseCase(instance()) }
    bindProvider { SetNotificationsEnabledUseCase(instance()) }
}

val mapperModule = DI.Module("Mapper module") {
    bindProvider { RegistrationResponseMapper(instance()) }
    bindProvider { DashboardResponseToCacheMapper(instance()) }
    bindProvider { DashboardCacheToResponseMapper(instance()) }
    bindProvider { ActivityTrackerResponseToCacheMapper(instance()) }
    bindProvider { ActivityTrackerCacheToResponseMapper(instance()) }
    bindProvider { ProfileResponseToCacheMapper(instance()) }
    bindProvider { ProfileCacheToResponseMapper(instance()) }
}
