package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompleteAccountStepRequest(
    val sex: String?,
    @SerialName("date_of_birth")
    val dateOfBirth: LocalDate?,
    val weight: Int?,
    val height: Int?
) : BaseRequest
