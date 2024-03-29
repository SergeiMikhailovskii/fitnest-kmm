package com.fitnest.presentation.decompose.onboarding.page

import com.arkivanov.decompose.value.Value
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

interface OnboardingPageComponent {
    val model: Value<Model>

    fun submitStep()

    data class Model(
        val image: ImageResource,
        val title: StringResource,
        val description: StringResource,
        val progress: Float
    )
}
