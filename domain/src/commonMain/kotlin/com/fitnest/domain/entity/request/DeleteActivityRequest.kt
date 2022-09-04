package com.fitnest.domain.entity.request

import com.fitnest.domain.enum.ActivityType
import kotlinx.serialization.Serializable

@Serializable
class DeleteActivityRequest(
    val id: Int,
    val type: ActivityType
)
