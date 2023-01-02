package com.fitnest.android.di

import androidx.lifecycle.ViewModelProvider
import com.fitnest.android.extension.ViewModelFactory
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.internal.FacebookService
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.android.internal.SnackbarDelegate
import com.fitnest.android.mapper.DateMapper
import com.fitnest.android.screen.login.LoginViewMapper
import com.fitnest.android.screen.login.LoginViewModel
import com.fitnest.android.screen.onboarding.OnboardingViewModel
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerViewMapper
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerViewModel
import com.fitnest.android.screen.private_area.home.HomeViewMapper
import com.fitnest.android.screen.private_area.home.HomeViewModel
import com.fitnest.android.screen.private_area.notification.NotificationsViewMapper
import com.fitnest.android.screen.private_area.notification.NotificationsViewModel
import com.fitnest.android.screen.private_area.settings.SettingsViewMapper
import com.fitnest.android.screen.private_area.settings.SettingsViewModel
import com.fitnest.android.screen.proxy.ProxyViewModel
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationViewMapper
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationViewModel
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewMapper
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewModel
import com.fitnest.android.screen.registration.goal.GoalRegistrationViewMapper
import com.fitnest.android.screen.registration.goal.GoalRegistrationViewModel
import com.fitnest.android.screen.registration.welcome_back.WelcomeBackRegistrationViewModel
import com.fitnest.android.screen.splash.SplashViewModel
import com.fitnest.domain.di.useCaseModule
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.mapper.db.ActivityTrackerCacheToResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapper
import com.fitnest.domain.mapper.db.DashboardCacheToResponseMapper
import com.fitnest.domain.mapper.db.DashboardResponseToCacheMapper
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.auth.ForgetPasswordUseCase
import com.fitnest.domain.usecase.auth.GetLoginPageUseCase
import com.fitnest.domain.usecase.auth.LoginUserUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStepUseCase
import com.fitnest.domain.usecase.private_area.AddActivityUseCase
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
import com.fitnest.domain.usecase.validation.LoginPageValidationUseCase
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bindFactory<DI, ViewModelProvider.Factory> { di ->
        ViewModelFactory(di)
    }
}

val registrationModule = DI.Module("registration module") {
    bindSingleton { RegistrationScreenState() }
}

val privateAreaModule = DI.Module("private area module") {
    import(notificationsPrivateAreaModule)
    import(settingsPrivateAreaModule)
}

val notificationsPrivateAreaModule = DI.Module("notifications private area module") {
    bindProvider {
        NotificationsViewModel(instance(), instance(), instance(), instance(), instance())
    }
    bindProvider { NotificationsViewMapper(instance()) }
}

val settingsPrivateAreaModule = DI.Module("settings private area module") {
    bindProvider { SettingsViewModel(instance(), instance(), instance()) }
    bindProvider { SettingsViewMapper(instance()) }
}

val serviceModule = DI.Module("service module") {
    bindSingleton { GoogleSignInService() }
    bindSingleton { FacebookService(instance()) }
    bindSingleton { SnackbarDelegate() }
    bindSingleton { ErrorHandlerDelegate(instance(), instance()) }
    import(com.fitnest.di.serviceModule)
}

val splashModule = DI.Module("splash module", allowSilentOverride = true) {
    bindProvider { SplashViewModel(instance()) }
    bindProvider { GenerateTokenUseCase(instance(), instance()) }
}

val proxyModule by lazy {
    DI.Module("proxy module", allowSilentOverride = true) {
        bindProvider { ProxyViewModel(instance(), instance(), instance()) }
        bindProvider { GenerateTokenUseCase(instance(), instance()) }
        bindProvider { GetOnboardingStepUseCase(instance(), instance(), instance()) }
        bindProvider { GetRegistrationStepData(instance(), instance(), instance(), instance()) }
    }
}

val onboardingModule by lazy {
    DI.Module("onboarding module") {
        bindProvider { OnboardingViewModel(instance(), instance()) }
        bindProvider { GetOnboardingStepUseCase(instance(), instance(), instance()) }
        bindProvider { SubmitOnboardingStepUseCase(instance(), instance()) }
    }
}

val loginScreenModule by lazy {
    DI.Module("login screen module") {
        bindProvider { LoginViewModel(instance(), instance(), instance(), instance(), instance()) }
        bindProvider { LoginViewMapper(instance()) }
        bindProvider { GetLoginPageUseCase(instance(), instance(), instance()) }
        bindProvider { LoginPageValidationUseCase() }
        bindProvider { ForgetPasswordUseCase(instance(), instance()) }
        bindProvider { LoginUserUseCase(instance(), instance()) }
    }
}

object RegistrationModule {
    val createAccountRegistrationScreenModule by lazy {
        DI.Module("create account registration module") {
            bindProvider {
                CreateAccountRegistrationViewModel(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
            bindProvider { CreateAccountRegistrationViewMapper() }
            bindProvider { CreateAccountRegistrationValidationUseCase() }
            bindProvider {
                SubmitRegistrationStepAndGetNextUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
        }
    }

    val completeAccountRegistrationScreenModule by lazy {
        DI.Module("complete account registration screen module") {
            bindProvider {
                CompleteAccountRegistrationViewModel(
                    instance(),
                    instance(),
                    instance(),
                    instance(),
                )
            }
            bindProvider { CompleteAccountRegistrationViewMapper() }
            bindProvider { CompleteAccountRegistrationValidationUseCase() }
            bindProvider {
                SubmitRegistrationStepAndGetNextUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
        }
    }

    val goalRegistrationScreenModule by lazy {
        DI.Module("goal registration screen module") {
            bindProvider { GoalRegistrationViewMapper() }
            bindProvider { GoalRegistrationViewModel(instance(), instance()) }
            bindProvider {
                SubmitRegistrationStepAndGetNextUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
        }
    }

    val welcomeBackRegistrationScreenModule by lazy {
        DI.Module("welcome back registration screen module") {
            bindProvider { WelcomeBackRegistrationViewModel(instance(), instance()) }
            bindProvider {
                SubmitRegistrationStepAndGetNextUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
        }
    }
}

object PrivateAreaModule {
    val dashboardPrivateAreaModule by lazy {
        DI.Module("dashboard private area module") {
            bindProvider { HomeViewModel(instance(), instance()) }
            bindProvider { HomeViewMapper(instance()) }
            bindProvider { DashboardResponseToCacheMapper(instance()) }
            bindProvider { DashboardCacheToResponseMapper(instance()) }
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
        }
    }

    val activityTrackerPrivateAreaModule by lazy {
        DI.Module("activity tracker private area module") {
            bindProvider {
                ActivityTrackerViewModel(
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
            bindProvider { ActivityTrackerViewMapper(instance(), instance()) }
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
            bindProvider { ActivityTrackerResponseToCacheMapper(instance()) }
            bindProvider { ActivityTrackerCacheToResponseMapper(instance()) }
            bindProvider { DateMapper() }
            bindProvider {
                DeleteActivityUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }
            bindProvider {
                AddActivityUseCase(
                    instance(),
                    instance(),
                    instance(),
                    instance(),
                    instance()
                )
            }

        }
    }
}
