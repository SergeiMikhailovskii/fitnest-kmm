package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import kotlinx.serialization.Serializable

@Serializable
class DeleteNotificationRequest(
    private val id: Int
) : BaseRequest
