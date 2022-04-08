package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountStepRequest(
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    val email: String?,
    val password: String?
) : BaseRequest