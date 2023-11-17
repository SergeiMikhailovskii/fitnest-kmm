package com.fitnest.presentation.decompose.registration.steps.completeAccount

import com.arkivanov.decompose.value.Value
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException

interface CompleteAccountRegistrationComponent {
    val model: Value<Model>

    fun submitRegistration()
    fun setSex(sexType: SexType)

    data class Model(
        val sexType: SexType? = null,
        val exception: CompleteAccountRegistrationScreenException = CompleteAccountRegistrationScreenException()
    )
}
