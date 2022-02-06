package com.fitnest.android.screen.registration.complete_account

import android.content.Context
import com.fitnest.android.base.BaseViewMapper
import com.fitnest.android.extension.format
import com.fitnest.domain.entity.request.CompleteAccountStepRequest

class CompleteAccountRegistrationViewMapper(
    private val context: Context
) : BaseViewMapper<CompleteAccountRegistrationScreenData, CompleteAccountStepRequest> {

    override fun mapScreenDataToStepRequestModel(
        data: CompleteAccountRegistrationScreenData
    ): CompleteAccountStepRequest {
        val sex = data.sex?.name
        val weight = data.weight
        val height = data.height
        val dateOfBirth = data.dateOfBirth?.format("dd/MM/yyyy")

        return CompleteAccountStepRequest(
            sex = sex,
            dateOfBirth = dateOfBirth,
            weight = weight,
            height = height
        )
    }
}