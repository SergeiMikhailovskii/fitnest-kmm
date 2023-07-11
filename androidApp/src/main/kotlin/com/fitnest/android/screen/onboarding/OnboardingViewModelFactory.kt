package com.fitnest.android.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitnest.presentation.screen.onboarding.OnboardingViewModel
import org.kodein.di.DI
import org.kodein.di.instance

class OnboardingViewModelFactory(
    private val params: Params
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm by params.di.instance<String, OnboardingViewModel>(arg = params.stepName)
        return vm as T
    }

    class Params(
        val stepName: String,
        val di: DI
    )
}
