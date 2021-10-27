package com.fitnest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val login: String? = null,
    val password: String? = null
)
