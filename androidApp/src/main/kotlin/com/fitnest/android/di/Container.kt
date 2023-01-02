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
import com.fitnest.domain.usecase.GenerateTokenUseCase
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val androidModule = DI.Module("android module") {
    bindSingleton { DateMapper() }
}

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bindFactory<DI, ViewModelProvider.Factory> { di ->
        ViewModelFactory(di)
    }
    bindProvider { OnboardingViewModel(instance(), instance()) }
    bindProvider { ProxyViewModel(instance(), instance(), instance()) }
}

val registrationModule = DI.Module("registration module") {
    import(createAccountRegistrationScreenModule)
    import(completeAccountRegistrationScreenModule)
    import(goalRegistrationScreenModule)
    import(welcomeBackRegistrationScreenModule)
    import(loginScreenModule)

    bindSingleton { RegistrationScreenState() }
}

val createAccountRegistrationScreenModule = DI.Module("create account registration screen module") {
    bindProvider {
        CreateAccountRegistrationViewModel(instance(), instance(), instance(), instance())
    }
    bindProvider { CreateAccountRegistrationViewMapper() }
}

val completeAccountRegistrationScreenModule =
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
    }

val goalRegistrationScreenModule = DI.Module("goal registration screen module") {
    bindProvider { GoalRegistrationViewMapper() }
    bindProvider { GoalRegistrationViewModel(instance(), instance()) }
}

val loginScreenModule = DI.Module("login screen module") {
    bindProvider { LoginViewModel(instance(), instance(), instance(), instance(), instance()) }
    bindProvider { LoginViewMapper(instance()) }
}

val welcomeBackRegistrationScreenModule = DI.Module("welcome back registration screen module") {
    bindProvider { WelcomeBackRegistrationViewModel(instance(), instance()) }
}

val privateAreaModule = DI.Module("private area module") {
    import(dashboardPrivateAreaModule)
    import(notificationsPrivateAreaModule)
    import(activityTrackerPrivateAreaModule)
    import(settingsPrivateAreaModule)
}

val dashboardPrivateAreaModule = DI.Module("dashboard private area module") {
    bindProvider { HomeViewModel(instance(), instance()) }
    bindProvider { HomeViewMapper(instance()) }
}

val notificationsPrivateAreaModule = DI.Module("notifications private area module") {
    bindProvider {
        NotificationsViewModel(instance(), instance(), instance(), instance(), instance())
    }
    bindProvider { NotificationsViewMapper(instance()) }
}

val activityTrackerPrivateAreaModule = DI.Module("activity tracker private area module") {
    bindProvider { ActivityTrackerViewModel(instance(), instance(), instance(), instance()) }
    bindProvider { ActivityTrackerViewMapper(instance(), instance()) }
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
