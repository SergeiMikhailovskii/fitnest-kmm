package com.fitnest.domain.entity.base

import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.functional.Failure
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class BaseResponse(
    val data: JsonElement? = null,
    val errors: List<Failure.ValidationError>? = null,
    private val flow: String? = null
) {

    fun getFlow() = FlowType.fromName(flow)
}
