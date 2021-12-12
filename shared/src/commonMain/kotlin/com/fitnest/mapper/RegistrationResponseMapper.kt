package com.fitnest.mapper

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.validator.MinLengthValidator
import com.fitnest.domain.entity.validator.RegExpValidator
import com.fitnest.domain.entity.validator.RequiredValidator
import com.fitnest.domain.entity.validator.Validator
import kotlinx.serialization.json.*

class RegistrationResponseMapper : ResponseMapper<JsonObject, GetRegistrationResponseData> {

    override fun map(source: JsonObject?): GetRegistrationResponseData {
        val step = source?.get("step")?.jsonPrimitive?.content
        val fields = source?.get("fields")?.jsonObject
//        val validationSchema = source?.get("validation_schema")?.jsonObject
        mapValidationSchema(source?.get("validation_schema")?.jsonObject)
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

    private fun mapValidationSchema(map: JsonObject?): Map<String, List<Validator?>> {
        val mappedValidationSchema = mutableMapOf<String, List<Validator?>>()
        val entries = map?.entries
        entries?.forEach {
            val validators = it.value
            val mappedValidators = mutableListOf<Validator?>()
            if (validators is JsonArray) {
                validators.forEach {
                    mappedValidators.add(mapValidator(it as JsonObject))
                }
            }
            mappedValidationSchema[it.key] = mappedValidators
        }
        return mappedValidationSchema
    }

    private fun mapValidator(validator: JsonObject): Validator? {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return when (validator["type"]?.jsonPrimitive?.content) {
            "required" -> {
                json.decodeFromJsonElement<RequiredValidator>(validator)
            }
            "regExp" -> {
                json.decodeFromJsonElement<RegExpValidator>(validator)
            }
            "minLength" -> {
                json.decodeFromJsonElement<MinLengthValidator>(validator)
            }
            else -> {
                null
            }
        }
    }
}