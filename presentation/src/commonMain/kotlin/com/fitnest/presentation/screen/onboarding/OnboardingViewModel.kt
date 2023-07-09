package com.fitnest.presentation.screen.onboarding

import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.onboarding.SubmitOnboardingStepUseCase
import com.fitnest.presentation.MR
import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    currentStep: String,
    private val getOnboardingStepUseCase: GetOnboardingStepUseCase,
    private val submitOnboardingStepUseCase: SubmitOnboardingStepUseCase,
) : BaseViewModel() {

    private val screenState = when (currentStep) {
        STEP_FIRST_ONBOARDING -> {
            OnboardingState(
                imageRes = "ic_onboarding_first.xml",
                title = MR.strings.onboarding_first_title,
                description = MR.strings.onboarding_first_description,
                progress = OnboardingState.FIRST_SCREEN_PROGRESS,
                previousProgress = OnboardingState.ZERO_SCREEN_PROGRESS
            )
        }

        STEP_SECOND_ONBOARDING -> {
            OnboardingState(
                imageRes = "ic_onboarding_second.xml",
                title = MR.strings.onboarding_second_title,
                description = MR.strings.onboarding_second_description,
                progress = OnboardingState.SECOND_SCREEN_PROGRESS,
                previousProgress = OnboardingState.FIRST_SCREEN_PROGRESS
            )
        }

        STEP_THIRD_ONBOARDING -> {
            OnboardingState(
                imageRes = "ic_onboarding_third.xml",
                title = MR.strings.onboarding_third_title,
                description = MR.strings.onboarding_third_description,
                progress = OnboardingState.THIRD_SCREEN_PROGRESS,
                previousProgress = OnboardingState.SECOND_SCREEN_PROGRESS
            )
        }

        STEP_FORTH_ONBOARDING -> {
            OnboardingState(
                imageRes = "ic_onboarding_forth.xml",
                title = MR.strings.onboarding_forth_title,
                description = MR.strings.onboarding_forth_description,
                progress = OnboardingState.FORTH_SCREEN_PROGRESS,
                previousProgress = OnboardingState.THIRD_SCREEN_PROGRESS
            )
        }

        else -> error("unknown step $currentStep")
    }

    private val _screenDataFlow = MutableStateFlow(screenState)
    val screenDataFlow = _screenDataFlow.asStateFlow()

    override val exceptionHandler = CoroutineExceptionHandler { _, failure ->
        if (failure is Failure.OnboardingFinished) {
            handleRoute(com.fitnest.presentation.navigation.Route.Proxy())
        } else if (failure is Failure) {
            super.handleFailure(failure)
        }
    }

    fun navigateToNextScreen() {
        viewModelScope.launch(exceptionHandler) {
            submitOnboardingStepUseCase().getOrThrow()
            val nextStep = getOnboardingStepUseCase().getOrThrow()
            nextStep?.let { handleRoute(com.fitnest.presentation.navigation.Route.OnboardingStep(it)) }
        }
    }

    companion object OnboardingSteps {
        const val STEP_FIRST_ONBOARDING = "STEP_FIRST_ONBOARDING"
        const val STEP_SECOND_ONBOARDING = "STEP_SECOND_ONBOARDING"
        const val STEP_THIRD_ONBOARDING = "STEP_THIRD_ONBOARDING"
        const val STEP_FORTH_ONBOARDING = "STEP_FORTH_ONBOARDING"
    }

}