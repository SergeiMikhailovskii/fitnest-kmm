package com.fitnest.domain.entity.base

import com.fitnest.domain.enum.FlowType
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val data: HashMap<String, @Polymorphic Any?>?,
    val errors: List<HashMap<String, String>>?,
    private val flow: String?
) {

    fun getFlow() = FlowType.fromName(flow)
}
