package com.mikhailovskii.kmmtest.android

import android.app.Application
import com.mikhailovskii.kmmtest.android.di.appModule
import com.mikhailovskii.kmmtest.android.di.viewModelModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application()/*, DIAware*/ {

//    override val di by DI.lazy {
//        import(appModule)
//    }

}