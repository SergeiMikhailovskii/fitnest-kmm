package com.fitnest.android.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitnest.android.R
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.entity.OnboardingState
import com.fitnest.domain.usecase.GetOnboardingStep
import com.fitnest.domain.usecase.SubmitOnboardingStep

class OnboardingViewModel(
    private val getOnboardingStepUseCase: GetOnboardingStep,
    private val submitOnboardingStepUseCase: SubmitOnboardingStep,
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<OnboardingState>()
    internal val stateLiveData: LiveData<OnboardingState> = _stateLiveData

    internal fun getOnboardingStep() {
        getOnboardingStepUseCase {
            it.either(::handleFailure) {
                it?.let {
                    handleRoute(Route.OnboardingStep(it))
                }
            }
        }
    }

    internal fun updateScreenState(stepName: String) {
        when (stepName) {
            "STEP_FIRST_ONBOARDING" -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_first,
                    title = R.string.onboarding_first_title,
                    description = R.string.onboarding_first_description,
                    progress = OnboardingState.FIRST_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.ZERO_SCREEN_PROGRESS
                )
            }
            "STEP_SECOND_ONBOARDING" -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_second,
                    title = R.string.onboarding_second_title,
                    description = R.string.onboarding_second_description,
                    progress = OnboardingState.SECOND_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.FIRST_SCREEN_PROGRESS
                )
            }
            "STEP_THIRD_ONBOARDING" -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_third,
                    title = R.string.onboarding_third_title,
                    description = R.string.onboarding_third_description,
                    progress = OnboardingState.THIRD_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.SECOND_SCREEN_PROGRESS
                )
            }
            "STEP_FORTH_ONBOARDING" -> {
                _stateLiveData.value = OnboardingState(
                    imageResId = R.drawable.ic_onboarding_forth,
                    title = R.string.onboarding_forth_title,
                    description = R.string.onboarding_forth_description,
                    progress = OnboardingState.FORTH_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.THIRD_SCREEN_PROGRESS
                )
            }
        }
        println("VM previousProgress=${_stateLiveData.value?.previousProgress}, progress=${_stateLiveData.value?.progress}")

    }

    internal fun navigateToNextScreen() {
        submitOnboardingStepUseCase {
            it.either(::handleFailure) {
                getOnboardingStepUseCase {
                    it.either(::handleFailure) {
                        it?.let {
                            if (it == "STEP_AFTER_ONBOARDING") {
                                handleRoute(Route.Onboarding)
                            } else {
                                handleRoute(Route.OnboardingStep(it))
                            }
                        }
                    }
                }
            }
        }
    }

}