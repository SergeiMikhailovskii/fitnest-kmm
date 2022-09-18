package com.fitnest.android.screen.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fitnest.android.R
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.entity.OnboardingState
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStepUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getOnboardingStepUseCase: GetOnboardingStepUseCase,
    private val submitOnboardingStepUseCase: SubmitOnboardingStepUseCase,
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<OnboardingState>()
    internal val stateLiveData: LiveData<OnboardingState> = _stateLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, failure ->
        if (failure is Failure.ValidationErrors && failure.fields.any { it.message == "onboarding.finished" }) {
            handleRoute(Route.Proxy())
        }
    }

    internal fun updateScreenState(stepName: String) {
        _stateLiveData.value = when (stepName) {
            STEP_FIRST_ONBOARDING -> {
                OnboardingState(
                    imageResId = R.drawable.ic_onboarding_first,
                    title = R.string.onboarding_first_title,
                    description = R.string.onboarding_first_description,
                    progress = OnboardingState.FIRST_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.ZERO_SCREEN_PROGRESS
                )
            }
            STEP_SECOND_ONBOARDING -> {
                OnboardingState(
                    imageResId = R.drawable.ic_onboarding_second,
                    title = R.string.onboarding_second_title,
                    description = R.string.onboarding_second_description,
                    progress = OnboardingState.SECOND_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.FIRST_SCREEN_PROGRESS
                )
            }
            STEP_THIRD_ONBOARDING -> {
                OnboardingState(
                    imageResId = R.drawable.ic_onboarding_third,
                    title = R.string.onboarding_third_title,
                    description = R.string.onboarding_third_description,
                    progress = OnboardingState.THIRD_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.SECOND_SCREEN_PROGRESS
                )
            }
            STEP_FORTH_ONBOARDING -> {
                OnboardingState(
                    imageResId = R.drawable.ic_onboarding_forth,
                    title = R.string.onboarding_forth_title,
                    description = R.string.onboarding_forth_description,
                    progress = OnboardingState.FORTH_SCREEN_PROGRESS,
                    previousProgress = OnboardingState.THIRD_SCREEN_PROGRESS
                )
            }
            else -> null
        }

    }

    internal fun navigateToNextScreen() {
        viewModelScope.launch(exceptionHandler) {
            submitOnboardingStepUseCase().getOrThrow()
            val nextStep = getOnboardingStepUseCase().getOrThrow()
            _stateLiveData.value = null
            nextStep?.let { handleRoute(Route.OnboardingStep(it)) }
        }
    }

    companion object OnboardingSteps {
        const val STEP_FIRST_ONBOARDING = "STEP_FIRST_ONBOARDING"
        const val STEP_SECOND_ONBOARDING = "STEP_SECOND_ONBOARDING"
        const val STEP_THIRD_ONBOARDING = "STEP_THIRD_ONBOARDING"
        const val STEP_FORTH_ONBOARDING = "STEP_FORTH_ONBOARDING"
    }

}