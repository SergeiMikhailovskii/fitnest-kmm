package com.fitnest.android.screen.registration.complete_account

import android.content.Context
import com.fitnest.android.R
import com.fitnest.android.base.BaseViewMapper
import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.enum.SexType

class CompleteAccountRegistrationViewMapper(
    private val context: Context
) :
    BaseViewMapper<CompleteAccountRegistrationScreenData, BaseRequest> {

    override fun mapScreenDataToStepRequestModel(data: CompleteAccountRegistrationScreenData): BaseRequest {
        TODO("Not yet implemented")
    }

    fun mapSexStringToEnumField(sex: String) =
        if (sex == context.getString(R.string.registration_complete_account_sex_male)) SexType.MALE
        else SexType.FEMALE
}