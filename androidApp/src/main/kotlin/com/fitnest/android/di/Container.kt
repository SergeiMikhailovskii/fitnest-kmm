package com.fitnest.android.di

import androidx.lifecycle.ViewModelProvider
import com.fitnest.android.extension.ViewModelFactory
import com.fitnest.android.screen.login.LoginViewModel
import com.fitnest.android.screen.onboarding.OnboardingViewModel
import com.fitnest.android.screen.proxy.ProxyViewModel
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewMapper
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationViewModel
import com.fitnest.android.screen.splash.SplashViewModel
import com.fitnest.domain.di.useCaseModule
import com.fitnest.domain.di.validatorModule
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)
    import(validatorModule)
    import(viewMapperModule)

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
    bind<CreateAccountRegistrationViewModel>() with factory {
        CreateAccountRegistrationViewModel(instance(), instance(), instance(), instance())
    }
    bind<ProxyViewModel>() with factory {
        ProxyViewModel(instance(), instance(), instance())
    }
}

val stateModule = DI.Module("state module") {
    bind<RegistrationScreenState>() with singleton {
        RegistrationScreenState()
    }
}

val viewMapperModule = DI.Module("view mapper module") {
    bind<CreateAccountRegistrationViewMapper>() with factory {
        CreateAccountRegistrationViewMapper()
    }
}