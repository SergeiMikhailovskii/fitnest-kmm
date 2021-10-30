package com.fitnest.domain.entity.base

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val data: HashMap<String, @Polymorphic Any?>?
)
