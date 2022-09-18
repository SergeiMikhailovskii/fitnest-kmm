package com.fitnest.domain.mapper

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class RegistrationResponseMapper(
    private val json: Json
) : Mapper<JsonObject, GetRegistrationResponseData> {

    operator fun invoke(source: JsonObject?) = map(source)

    override fun map(source: JsonObject?): GetRegistrationResponseData {
        val step = source?.get("step")?.jsonPrimitive?.content
        val fields = source?.get("fields")?.jsonObject

        val stepData = mapStepData(fields, step)

        val jsonSchemaElement = source?.get("validation_schema")
        val validationSchema = if (jsonSchemaElement is JsonNull) null
        else mapValidationSchema(jsonSchemaElement?.jsonObject, step)

        return GetRegistrationResponseData(
            step = step,
            fields = stepData,
            validationSchema = validationSchema
        )
    }

    private fun mapStepData(fields: JsonObject?, step: String?): RegistrationStepModel? {
        if (fields == null) return null
        return when (step) {
            "STEP_CREATE_ACCOUNT" -> {
                return json.decodeFromJsonElement<RegistrationStepModel.CreateAccountStepModel>(
                    fields
                )
            }
            "STEP_WELCOME_BACK" -> {
                return json.decodeFromJsonElement<RegistrationStepModel.WelcomeBackStepModel>(fields)
            }
            else -> null
        }
    }

    private fun mapValidationSchema(
        schema: JsonObject?,
        step: String?
    ): RegistrationStepValidationSchema? {
        if (schema == null) return null
        return when (step) {
            "STEP_CREATE_ACCOUNT" -> {
                return json.decodeFromJsonElement<RegistrationStepValidationSchema.CreateAccountStepValidationSchema>(
                    schema
                )
            }
            "STEP_COMPLETE_ACCOUNT" -> {
                return json.decodeFromJsonElement<RegistrationStepValidationSchema.CompleteAccountStepValidationSchema>(
                    schema
                )
            }
            else -> null
        }
    }
}
