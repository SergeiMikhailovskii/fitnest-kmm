package com.fitnest.android.di

import androidx.lifecycle.ViewModelProvider
import com.fitnest.android.extension.ViewModelFactory
import com.fitnest.android.screen.login.LoginViewModel
import com.fitnest.android.screen.onboarding.OnboardingViewModel
import com.fitnest.android.screen.private_area.home.HomeViewMapper
import com.fitnest.android.screen.private_area.home.HomeViewModel
import com.fitnest.android.screen.proxy.ProxyViewModel
import com.fitnest.android.screen.registration.RegistrationScreenState
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
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(di)
    }
    bind<LoginViewModel>() with factory {
        LoginViewModel(instance())
    }
    bind<SplashViewModel>() with factory {
        SplashViewModel(instance())
    }
    bind<OnboardingViewModel>() with factory {
        OnboardingViewModel(instance(), instance())
    }
    bind<ProxyViewModel>() with factory {
        ProxyViewModel(instance(), instance(), instance(), instance())
    }
}

val registrationModule = DI.Module("registration module") {
    import(createAccountRegistrationScreenModule)
    import(completeAccountRegistrationScreenModule)
    import(goalRegistrationScreenModule)
    import(welcomeBackRegistrationScreenModule)

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

val welcomeBackRegistrationScreenModule = DI.Module("welcome back registration screen module") {
    bind<WelcomeBackRegistrationViewModel>() with factory {
        WelcomeBackRegistrationViewModel(instance(), instance())
    }
}

val privateAreaModule = DI.Module("private area module") {
    import(dashboardPrivateAreaModule)
}

val dashboardPrivateAreaModule = DI.Module("dashboard private area module") {
    bind<HomeViewModel>() with factory {
        HomeViewModel(instance(), instance())
    }
    bind<HomeViewMapper>() with factory {
        HomeViewMapper()
    }
}