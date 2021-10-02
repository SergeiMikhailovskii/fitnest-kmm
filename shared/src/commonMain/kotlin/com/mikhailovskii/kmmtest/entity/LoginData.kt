package com.mikhailovskii.kmmtest.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val login: String? = null,
    val password: String? = null
)
