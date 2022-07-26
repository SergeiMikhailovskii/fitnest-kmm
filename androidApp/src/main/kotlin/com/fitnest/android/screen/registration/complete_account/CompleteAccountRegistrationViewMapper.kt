package com.fitnest.android.screen.registration.complete_account

import com.fitnest.android.base.BaseViewMapper
import com.fitnest.android.extension.format
import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import kotlinx.datetime.LocalDate

class CompleteAccountRegistrationViewMapper :
    BaseViewMapper<CompleteAccountRegistrationScreenData, CompleteAccountStepRequest> {

    override fun mapScreenDataToStepRequestModel(
        data: CompleteAccountRegistrationScreenData
    ): CompleteAccountStepRequest {
        val sex = data.sex?.name
        val weight = data.weight
        val height = data.height
        val dateOfBirth = if (data.dateOfBirth == null) null
        else LocalDate.parse(data.dateOfBirth?.format("yyyy-MM-dd").orEmpty())

        return CompleteAccountStepRequest(
            sex = sex,
            dateOfBirth = dateOfBirth,
            weight = weight,
            height = height
        )
    }
}