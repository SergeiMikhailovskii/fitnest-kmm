package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.enum.GoalType
import kotlinx.serialization.Serializable

@Serializable
data class GoalStepRequest(
    val goal: GoalType?
) : BaseRequest
