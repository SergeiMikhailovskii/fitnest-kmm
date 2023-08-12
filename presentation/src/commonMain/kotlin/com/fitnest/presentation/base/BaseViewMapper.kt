package com.fitnest.presentation.base

import com.fitnest.domain.entity.base.BaseRequest

interface BaseViewMapper<From, To : BaseRequest> {
    fun mapScreenDataToStepRequestModel(data: From): To
}
