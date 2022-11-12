package com.fitnest.android.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.di.androidModule
import com.fitnest.android.di.privateAreaModule
import com.fitnest.android.di.registrationModule
import com.fitnest.android.di.serviceModule
import com.fitnest.android.di.viewModelModule
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.di.dataExceptionHandlerModule
import com.fitnest.di.databaseModule
import com.fitnest.di.repositoryModule
import com.fitnest.di.serializationModule
import com.fitnest.domain.di.mapperModule
import com.google.accompanist.pager.ExperimentalPagerApi
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DIContext
import org.kodein.di.bind
import org.kodein.di.diContext
import org.kodein.di.instance
import kotlin.time.ExperimentalTime

@ExperimentalMaterial3Api
@ExperimentalTime
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
class MainActivity : AppCompatActivity(), DIAware {

    override val diContext: DIContext<*> = diContext(this)

    override val di by DI.lazy {
        bind<Context>() with instance(this@MainActivity)
        import(dataExceptionHandlerModule)
        import(databaseModule)
        import(registrationModule)
        import(privateAreaModule)
        import(viewModelModule)
        import(repositoryModule)
        import(serviceModule)
        import(mapperModule)
        import(serializationModule)
        import(androidModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initServices()
        setContent {
            FitnestApp()
        }
    }

    private fun initServices() {
        val googleSignInService: GoogleSignInService by di.instance()
        googleSignInService.init()
    }

}
