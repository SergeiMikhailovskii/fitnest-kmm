package com.mikhailovskii.kmmtest.android.di

import androidx.lifecycle.ViewModelProvider
import com.mikhailovskii.kmmtest.android.extension.ViewModelFactory
import com.mikhailovskii.kmmtest.android.view.login.LoginViewModel
import com.mikhailovskii.kmmtest.android.view.onboarding.OnboardingViewModel
import com.mikhailovskii.kmmtest.android.view.splash.SplashViewModel
import com.mikhailovskii.kmmtest.di.useCaseModule
import com.mikhailovskii.kmmtest.di.repositoryModule
import com.mikhailovskii.kmmtest.di.serviceModule
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)
    import(repositoryModule)
    import(serviceModule)

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
        OnboardingViewModel()
    }
}
