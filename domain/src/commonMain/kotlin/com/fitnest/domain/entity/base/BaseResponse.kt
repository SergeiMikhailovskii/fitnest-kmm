package com.fitnest.domain.entity.base

import com.fitnest.domain.enum.FlowType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class BaseResponse(
    val data: JsonElement,
    val errors: List<HashMap<String, String>>?,
    private val flow: String?
) {

    fun getFlow() = FlowType.fromName(flow)
}
