package com.fitnest.android.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.fitnest.android.FitnestApplication
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.internal.GoogleSignInService
import com.google.accompanist.pager.ExperimentalPagerApi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.kodein.di.DIAware
import org.kodein.di.instance
import kotlin.time.ExperimentalTime

@ExperimentalMaterial3Api
@ExperimentalTime
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
class MainActivity : AppCompatActivity(), DIAware {

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
