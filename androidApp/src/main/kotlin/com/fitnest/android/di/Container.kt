package com.fitnest.android.di

import androidx.lifecycle.ViewModelProvider
import com.fitnest.domain.di.useCaseModule
import com.fitnest.android.extension.ViewModelFactory
import com.fitnest.android.view.login.LoginViewModel
import com.fitnest.android.view.onboarding.OnboardingViewModel
import com.fitnest.android.view.registration.CreateAccountRegistrationViewModel
import com.fitnest.android.view.splash.SplashViewModel
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(instance())
    }
    bind<LoginViewModel>() with multiton {
        LoginViewModel(instance())
    }
    bind<SplashViewModel>() with multiton {
        SplashViewModel(instance())
    }
    bind<OnboardingViewModel>() with multiton {
        OnboardingViewModel(instance(), instance())
    }
    bind<CreateAccountRegistrationViewModel>() with multiton {
        CreateAccountRegistrationViewModel()
    }
}
