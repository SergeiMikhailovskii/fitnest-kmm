package com.fitnest.android.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fitnest.android.R
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.entity.OnboardingState
import com.fitnest.domain.usecase.GetOnboardingStep
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getOnboardingStepUseCase: GetOnboardingStep,
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<OnboardingState>()
    internal val stateLiveData: LiveData<OnboardingState> = _stateLiveData

    internal fun getOnboardingStep() {
        getOnboardingStepUseCase {
            it.either(::handleFailure) {
                println(it)
            }
        }
    }

    internal fun updateScreenState(progress: Int) {
        when (progress) {
            OnboardingState.FIRST_SCREEN_PROGRESS -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_first,
                    title = R.string.onboarding_first_title,
                    description = R.string.onboarding_first_description,
                    progress = progress
                )
            }
            OnboardingState.SECOND_SCREEN_PROGRESS -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_second,
                    title = R.string.onboarding_second_title,
                    description = R.string.onboarding_second_description,
                    progress = progress
                )
            }
            OnboardingState.THIRD_SCREEN_PROGRESS -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_third,
                    title = R.string.onboarding_third_title,
                    description = R.string.onboarding_third_description,
                    progress = progress
                )
            }
            OnboardingState.FORTH_SCREEN_PROGRESS -> {
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
        if (progress == OnboardingState.ONBOARDING_MAX_PROGRESS) {
            viewModelScope.launch {
                handleRoute(Route.Unknown)
            }
        } else {
            handleRoute(Route.OnboardingStep(progress + 1))
        }
    }

}