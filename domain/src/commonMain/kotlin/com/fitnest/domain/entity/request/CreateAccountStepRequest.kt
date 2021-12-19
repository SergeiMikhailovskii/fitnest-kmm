package com.fitnest.domain.entity.request

import com.fitnest.domain.entity.base.BaseRequest

data class CreateAccountStepRequest(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
) : BaseRequest {

    override fun toMap() = mapOf(
        "firstName" to firstName,
        "lastName" to lastName,
        "email" to email,
        "password" to password,
    )
}