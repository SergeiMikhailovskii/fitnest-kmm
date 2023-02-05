package com.fitnest.android.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.fitnest.android.FitnestApplication
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.internal.GoogleSignInService
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napier.base(DebugAntilog("Napier"))
        initServices()
        setContent {
            FitnestApp()
        }
    }

    private fun initServices() {
        val googleSignInService: GoogleSignInService by instance()
        googleSignInService.init(this)
    }

    override val di by lazy {
        (application as FitnestApplication).di
    }

}
