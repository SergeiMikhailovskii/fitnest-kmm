package com.fitnest.mapper

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.validator.Validator
import kotlinx.serialization.json.*

class RegistrationResponseMapper(
    private val json: Json
) : ResponseMapper<JsonObject, GetRegistrationResponseData> {

    override fun map(source: JsonObject?): GetRegistrationResponseData {
        val step = source?.get("step")?.jsonPrimitive?.content
        val fields = source?.get("fields")?.jsonObject

        val stepData = mapStepData(fields, step)

        val jsonSchemaElement = source?.get("validation_schema")
        val validationSchema = if (jsonSchemaElement is JsonNull) null
        else mapValidationSchema(jsonSchemaElement?.jsonObject)

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

    private fun mapValidationSchema(map: JsonObject?): Map<String, List<Validator>> {
        val mappedValidationSchema = mutableMapOf<String, List<Validator>>()
        val entries = map?.entries
        entries?.forEach {
            val validators = it.value
            val mappedValidators = mutableListOf<Validator>()
            if (validators is JsonArray) {
                validators.forEach {
                    mappedValidators.add(json.decodeFromJsonElement(it))
                }
            }
            mappedValidationSchema[it.key] = mappedValidators
        }
        return mappedValidationSchema
    }

}