package com.mikhailovskii.kmmtest.android.view.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mikhailovskii.kmmtest.android.di.viewModelModule
import com.mikhailovskii.kmmtest.android.view.base.FitnestApp
import org.kodein.di.*


class MainActivity : ComponentActivity(), DIAware {

    override val diContext: DIContext<*> = diContext(this)

    override val di by DI.lazy {
        import(viewModelModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnestApp()
        }
    }

}
