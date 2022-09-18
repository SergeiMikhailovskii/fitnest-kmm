package com.fitnest.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
class GetOnboardingStepResponse(
    val step: String? = null
)
