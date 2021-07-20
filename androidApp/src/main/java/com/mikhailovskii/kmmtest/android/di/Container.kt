package com.mikhailovskii.kmmtest.android.di

import androidx.lifecycle.ViewModelProvider
import com.mikhailovskii.kmmtest.android.extension.ViewModelFactory
import com.mikhailovskii.kmmtest.android.view.LoginViewModel
import org.kodein.di.*

val viewModelModule = DI.Module("view model module") {
    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(instance())
    }
    bind<LoginViewModel>() with provider {
        LoginViewModel()
    }
}