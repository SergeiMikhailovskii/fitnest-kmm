package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import kotlinx.serialization.Serializable

@Serializable
class PinNotificationRequest(
    private val id: Int,
    private val pin: Boolean
) : BaseRequest