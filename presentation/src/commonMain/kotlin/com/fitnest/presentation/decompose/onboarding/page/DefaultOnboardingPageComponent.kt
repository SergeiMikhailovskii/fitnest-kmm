package com.fitnest.presentation.decompose.onboarding.page

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

class DefaultOnboardingPageComponent(
    image: ImageResource,
    title: StringResource,
    description: StringResource,
    progress: Float,
    private val onSubmit: () -> Unit
) : OnboardingPageComponent {

    private val _model = MutableValue(OnboardingPageComponent.Model(image, title, description, progress))
    override val model: Value<OnboardingPageComponent.Model> = _model

    override fun submitStep() {
        onSubmit()
    }
}
