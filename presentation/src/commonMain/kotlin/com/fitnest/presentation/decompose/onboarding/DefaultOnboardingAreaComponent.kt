package com.fitnest.presentation.decompose.onboarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.domain.usecase.onboarding.SubmitAndGetNextOnboardingStepUseCase
import com.fitnest.presentation.MR
import com.fitnest.presentation.decompose.onboarding.page.DefaultOnboardingPageComponent
import com.fitnest.presentation.extension.disposableScope
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultOnboardingAreaComponent(
    context: ComponentContext,
    initialStep: String,
    private val submitOnboardingStepUseCase: SubmitAndGetNextOnboardingStepUseCase
) : OnboardingAreaComponent,
    CoroutineScope by context.disposableScope(),
    ComponentContext by context {

    private val navigation = StackNavigation<PageConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = getStepByName(initialStep),
        handleBackButton = true,
        childFactory = ::createChild
    )

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private fun createChild(config: PageConfig, context: ComponentContext) = OnboardingAreaComponent.Page(
        DefaultOnboardingPageComponent(
            config.image,
            config.title,
            config.description,
            config.progress
        ) {
            launch(errorHandler) {
                val nextStep = submitOnboardingStepUseCase() ?: error("Step cannot be null")
                navigation.replaceCurrent(getStepByName(nextStep))
            }
        }
    )

    private fun getStepByName(stepName: String) = when (stepName) {
        "STEP_FIRST_ONBOARDING" -> PageConfig.First
        "STEP_SECOND_ONBOARDING" -> PageConfig.Second
        "STEP_THIRD_ONBOARDING" -> PageConfig.Third
        "STEP_FORTH_ONBOARDING" -> PageConfig.Forth
        else -> error("Unknown step - $stepName")
    }

    sealed class PageConfig(
        val image: ImageResource,
        val title: StringResource,
        val description: StringResource,
        val progress: Float
    ) : Parcelable {

        @Parcelize
        data object First : PageConfig(
            MR.images.ic_onboarding_first,
            MR.strings.onboarding_first_title,
            MR.strings.onboarding_first_description,
            0.25F
        )

        @Parcelize
        data object Second : PageConfig(
            MR.images.ic_onboarding_second,
            MR.strings.onboarding_second_title,
            MR.strings.onboarding_second_description,
            0.5F
        )

        @Parcelize
        data object Third : PageConfig(
            MR.images.ic_onboarding_third,
            MR.strings.onboarding_third_title,
            MR.strings.onboarding_third_description,
            0.75F
        )

        @Parcelize
        data object Forth : PageConfig(
            MR.images.ic_onboarding_forth,
            MR.strings.onboarding_forth_title,
            MR.strings.onboarding_forth_description,
            1F
        )
    }
}
