package com.mikhailovskii.kmmtest.android.di

import androidx.lifecycle.ViewModelProvider
import com.mikhailovskii.kmmtest.android.extension.ViewModelFactory
import com.mikhailovskii.kmmtest.android.view.login.LoginViewModel
import com.mikhailovskii.kmmtest.di.useCaseModule
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(instance())
    }
    bind<LoginViewModel>() with provider {
        LoginViewModel(instance())
    }
}
