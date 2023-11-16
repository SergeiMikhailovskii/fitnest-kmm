package com.fitnest.presentation.screen.registration.createAccount

import com.fitnest.domain.entity.request.CreateAccountStepRequest
import com.fitnest.presentation.base.BaseViewMapper
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationComponent

class CreateAccountRegistrationViewMapper :
    BaseViewMapper<CreateAccountRegistrationScreenData, CreateAccountStepRequest> {
    @Deprecated("Use fun mapScreenDataToStepRequestModel(data: CreateAccountRegistrationComponent.Model) instead")
    override fun mapScreenDataToStepRequestModel(
        data: CreateAccountRegistrationScreenData
    ) = CreateAccountStepRequest(
        firstName = data.firstName,
        lastName = data.lastName,
        email = data.email,
        password = data.password
    )

    fun mapScreenDataToStepRequestModel(
        data: CreateAccountRegistrationComponent.Model
    ) = CreateAccountStepRequest(
        firstName = data.firstName,
        lastName = data.lastName,
        email = data.email,
        password = data.password
    )
}
