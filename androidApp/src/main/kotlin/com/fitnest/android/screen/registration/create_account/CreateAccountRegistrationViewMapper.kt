package com.fitnest.android.screen.registration.create_account

import com.fitnest.android.base.BaseViewMapper
import com.fitnest.domain.entity.request.CreateAccountStepRequest

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