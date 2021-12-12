package com.fitnest.mapper

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.RegistrationStepModel
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class RegistrationResponseMapper : ResponseMapper<JsonObject, GetRegistrationResponseData> {

    override fun map(source: JsonObject?): GetRegistrationResponseData {
        val step = source?.get("step")?.jsonPrimitive?.content
        val fields = source?.get("fields")?.jsonObject
//        val validationSchema = source?.get("validation_schema")?.jsonObject
        val stepData = mapStepData(fields, step)
        return GetRegistrationResponseData(step = step, fields = stepData)
    }

    private fun mapStepData(fields: JsonObject?, step: String?): RegistrationStepModel? {
        return when (step) {
            "STEP_CREATE_ACCOUNT" -> {
                val firstName = fields?.get("first_name")?.jsonPrimitive?.content
                val lastName = fields?.get("last_name")?.jsonPrimitive?.content
                val password = fields?.get("password")?.jsonPrimitive?.content
                val email = fields?.get("email")?.jsonPrimitive?.content
                return RegistrationStepModel.CreateAccountStepModel(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
            }
            else -> null
        }
    }
}