package com.fitnest.presentation.screen.registration.complete_account.screen

import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import com.fitnest.domain.extension.dateToString
import com.fitnest.presentation.base.BaseViewMapper
import kotlinx.datetime.LocalDate

class CompleteAccountRegistrationViewMapper :
    BaseViewMapper<CompleteAccountRegistrationScreenData, CompleteAccountStepRequest> {

    override fun mapScreenDataToStepRequestModel(
        data: CompleteAccountRegistrationScreenData
    ): CompleteAccountStepRequest {
        val sex = data.sex?.name
        val weight = data.weight
        val height = data.height
        val dateOfBirth = data.dateOfBirth?.let { LocalDate.parse(it.dateToString("yyyy-MM-dd")) }

        return CompleteAccountStepRequest(
            sex = sex,
            dateOfBirth = dateOfBirth,
            weight = weight,
            height = height
        )
    }
}