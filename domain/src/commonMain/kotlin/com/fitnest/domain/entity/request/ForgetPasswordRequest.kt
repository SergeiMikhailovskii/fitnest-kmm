package com.fitnest.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
class ForgetPasswordRequest(
    private val login: String? = null
)
