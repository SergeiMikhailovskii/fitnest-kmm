package com.fitnest.presentation.decompose.registration.steps.completeAccount

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.fitnest.domain.enum.SexType

class DefaultCompleteAccountRegistrationComponent : CompleteAccountRegistrationComponent {

    private val _model = MutableValue(CompleteAccountRegistrationComponent.Model())
    override val model: Value<CompleteAccountRegistrationComponent.Model> = _model

    override fun submitRegistration() {
    }

    override fun setSex(sexType: SexType) {
        _model.value = _model.value.copy(sexType = sexType)
    }
}
