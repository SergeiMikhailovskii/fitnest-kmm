package com.mikhailovskii.kmmtest.android.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.mikhailovskii.kmmtest.android.App
import com.mikhailovskii.kmmtest.android.extension.ViewModelFactory
import com.mikhailovskii.kmmtest.android.view.login.LoginViewModel
import com.mikhailovskii.kmmtest.di.useCaseModule
import org.kodein.di.*

val appModule = DI.Module("app module") {
    import(viewModelModule)
    bind<Context>() with multiton { app: App ->
        app.applicationContext
    }
}

val viewModelModule = DI.Module("view model module") {
    import(useCaseModule)

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(instance())
    }
    bind<LoginViewModel>() with provider {
        LoginViewModel(instance())
    }
}
