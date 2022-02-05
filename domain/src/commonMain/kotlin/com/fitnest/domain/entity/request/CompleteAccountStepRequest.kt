package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import kotlinx.serialization.SerialName

data class CompleteAccountStepRequest(
    val sex: String?,
    @SerialName("date_of_birth")
    val dateOfBirth: String?,
    val weight: Int?,
    val height: Int?
) : BaseRequest
