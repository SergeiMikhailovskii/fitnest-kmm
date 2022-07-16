package com.fitnest.domain.entity.response

import com.fitnest.domain.entity.validator.Validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginPageResponse(
    val fields: LoginPageFields? = null,
    @SerialName("validation_schema")
    val validationSchema: LoginPageValidationSchema? = null
) {

    @Serializable
    class LoginPageFields(
        val login: String? = null,
        val password: String? = null
    )

    @Serializable
    class LoginPageValidationSchema(
        val login: List<Validator>,
        val password: List<Validator>
    )
}