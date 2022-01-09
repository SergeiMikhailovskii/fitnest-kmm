package com.fitnest.android.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.di.registrationModule
import com.fitnest.android.di.viewModelModule
import com.fitnest.di.*
import org.kodein.di.*

@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity(), DIAware {

    override val diContext: DIContext<*> = diContext(this)

    override val di by DI.lazy {
        bind<Context>() with instance(this@MainActivity)
        import(registrationModule)
        import(viewModelModule)
        import(repositoryModule)
        import(serviceModule)
        import(cookieModule)
        import(mapperModule)
        import(serializationModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnestApp()
        }
    }

}
