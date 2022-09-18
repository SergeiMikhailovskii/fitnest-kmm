package com.fitnest.android.di

import androidx.lifecycle.ViewModelProvider
import com.fitnest.android.extension.ViewModelFactory
import com.fitnest.android.internal.FacebookService
import com.fitnest.android.internal.GoogleSignInService
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
import com.fitnest.android.screen.proxy.ProxyViewModel
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationScreenData
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationViewMapper
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationViewModel
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewMapper
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewModel
import com.fitnest.android.screen.registration.goal.GoalRegistrationViewMapper
import com.fitnest.android.screen.registration.goal.GoalRegistrationViewModel
import com.fitnest.android.screen.registration.welcome_back.WelcomeBackRegistrationViewModel
import com.fitnest.android.screen.splash.SplashViewModel
import com.fitnest.domain.di.useCaseModule
import com.fitnest.domain.validator.CompleteAccountRegistrationValidator
import com.fitnest.domain.validator.CreateAccountRegistrationValidator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.singleton

val androidModule = DI.Module("android module") {
    bind<DateMapper>() with singleton {
        DateMapper()
    }
}

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(di)
    }
    bind<SplashViewModel>() with factory {
        SplashViewModel(instance())
    }
    bind<OnboardingViewModel>() with factory {
        OnboardingViewModel(instance(), instance())
    }
    bind<ProxyViewModel>() with factory {
        ProxyViewModel(instance(), instance(), instance())
    }
}

val registrationModule = DI.Module("registration module") {
    import(createAccountRegistrationScreenModule)
    import(completeAccountRegistrationScreenModule)
    import(goalRegistrationScreenModule)
    import(welcomeBackRegistrationScreenModule)
    import(loginScreenModule)

    bind<RegistrationScreenState>() with singleton {
        RegistrationScreenState()
    }
}

val createAccountRegistrationScreenModule = DI.Module("create account registration screen module") {
    bind<CreateAccountRegistrationViewModel>() with factory {
        CreateAccountRegistrationViewModel(instance(), instance(), instance(), instance())
    }
    bind<CreateAccountRegistrationViewMapper>() with factory {
        CreateAccountRegistrationViewMapper()
    }
    bind<CreateAccountRegistrationValidator>() with factory {
        CreateAccountRegistrationValidator()
    }
}

val completeAccountRegistrationScreenModule =
    DI.Module("complete account registration screen module") {
        bind<CompleteAccountRegistrationViewModel>() with factory {
            CompleteAccountRegistrationViewModel(
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind<CompleteAccountRegistrationViewMapper>() with singleton {
            CompleteAccountRegistrationViewMapper()
        }
        bind<CompleteAccountRegistrationScreenData>() with factory {
            CompleteAccountRegistrationScreenData.init()
        }
        bind<CompleteAccountRegistrationValidator>() with factory {
            CompleteAccountRegistrationValidator()
        }
    }

val goalRegistrationScreenModule = DI.Module("goal registration screen module") {
    bind<GoalRegistrationViewMapper>() with factory {
        GoalRegistrationViewMapper()
    }
    bind<GoalRegistrationViewModel>() with factory {
        GoalRegistrationViewModel(instance(), instance())
    }
}

val loginScreenModule = DI.Module("login screen module") {
    bind<LoginViewModel>() with factory {
        LoginViewModel(instance(), instance(), instance(), instance(), instance())
    }
    bind<LoginViewMapper>() with factory {
        LoginViewMapper(instance())
    }
}

val welcomeBackRegistrationScreenModule = DI.Module("welcome back registration screen module") {
    bind<WelcomeBackRegistrationViewModel>() with factory {
        WelcomeBackRegistrationViewModel(instance(), instance())
    }
}

val privateAreaModule = DI.Module("private area module") {
    import(dashboardPrivateAreaModule)
    import(notificationsPrivateAreaModule)
    import(activityTrackerPrivateAreaModule)
}

val dashboardPrivateAreaModule = DI.Module("dashboard private area module") {
    bind<HomeViewModel>() with factory {
        HomeViewModel(instance(), instance())
    }
    bind<HomeViewMapper>() with factory {
        HomeViewMapper(instance())
    }
}

val notificationsPrivateAreaModule = DI.Module("notifications private area module") {
    bind<NotificationsViewModel>() with factory {
        NotificationsViewModel(instance(), instance(), instance(), instance(), instance())
    }
    bind<NotificationsViewMapper>() with factory {
        NotificationsViewMapper(instance())
    }
}

val activityTrackerPrivateAreaModule = DI.Module("activity tracker private area module") {
    bind<ActivityTrackerViewModel>() with factory {
        ActivityTrackerViewModel(instance(), instance(), instance(), instance())
    }
    bind<ActivityTrackerViewMapper>() with factory {
        ActivityTrackerViewMapper(instance(), instance())
    }
}

val serviceModule = DI.Module("service module") {
    bind<GoogleSignInService>() with singleton {
        GoogleSignInService(instance())
    }
    bind<FacebookService>() with singleton {
        FacebookService(instance())
    }
    import(com.fitnest.di.serviceModule)
}