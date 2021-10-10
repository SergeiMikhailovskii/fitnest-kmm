package com.mikhailovskii.kmmtest.android.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.android.base.Route
import com.mikhailovskii.kmmtest.entity.OnboardingState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val _stateLiveData = MutableLiveData<OnboardingState>()
    internal val stateLiveData: LiveData<OnboardingState> = _stateLiveData

    private val _routeSharedFlow = MutableSharedFlow<Route>()
    internal val routeSharedFlow = _routeSharedFlow.asSharedFlow()

    internal fun updateScreenState(progress: Int) {
        when (progress) {
            1 -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_first,
                    title = R.string.onboarding_first_title,
                    description = R.string.onboarding_first_description,
                    progress = progress
                )
            }
            2 -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_second,
                    title = R.string.onboarding_second_title,
                    description = R.string.onboarding_second_description,
                    progress = progress
                )
            }
            3 -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_third,
                    title = R.string.onboarding_third_title,
                    description = R.string.onboarding_third_description,
                    progress = progress
                )
            }
            4 -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_forth,
                    title = R.string.onboarding_forth_title,
                    description = R.string.onboarding_forth_description,
                    progress = progress
                )
            }
        }

    }

    internal fun navigateToNextScreen() {
        val progress = _stateLiveData.value?.progress ?: 0
        if (progress == 4) {
            viewModelScope.launch {
                _routeSharedFlow.emit(Route.Unknown)
            }
        } else {
            viewModelScope.launch {
                _routeSharedFlow.emit(Route.Onboarding(progress + 1))
            }
        }
    }

}