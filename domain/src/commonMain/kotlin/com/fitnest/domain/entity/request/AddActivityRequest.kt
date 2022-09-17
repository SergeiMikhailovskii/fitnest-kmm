package com.fitnest.domain.entity.request

import com.fitnest.domain.enum.ActivityType
import kotlinx.serialization.Serializable

@Serializable
class AddActivityRequest(
    val amount: Int,
    val type: ActivityType
)
