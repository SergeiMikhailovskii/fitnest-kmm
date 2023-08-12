package com.fitnest.presentation.screen.registration.createAccount

import com.fitnest.domain.entity.request.CreateAccountStepRequest
import com.fitnest.presentation.base.BaseViewMapper

class CreateAccountRegistrationViewMapper :
    BaseViewMapper<CreateAccountRegistrationScreenData, CreateAccountStepRequest> {
    override fun mapScreenDataToStepRequestModel(
        data: CreateAccountRegistrationScreenData
    ) = CreateAccountStepRequest(
        firstName = data.firstName,
        lastName = data.lastName,
        email = data.email,
        password = data.password
    )
}
