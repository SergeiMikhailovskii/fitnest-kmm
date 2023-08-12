package com.fitnest.presentation.screen.registration.goal.data

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

data class GoalPageUIModel(
    val image: ImageResource,
    val title: StringResource,
    val description: StringResource
)
