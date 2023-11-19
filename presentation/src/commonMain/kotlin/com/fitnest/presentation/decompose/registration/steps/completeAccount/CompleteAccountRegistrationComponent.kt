package com.fitnest.presentation.decompose.registration.steps.completeAccount

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import com.fitnest.domain.internal.date.Date
import com.fitnest.presentation.decompose.dialog.DialogComponent

interface CompleteAccountRegistrationComponent {
    val model: Value<Model>
    val dialog: Value<ChildSlot<*, DialogComponent>>

    fun submitRegistration()
    fun setSex(sexType: SexType)
    fun showDateOfBirthPicker()

    data class Model(
        val sexType: SexType? = null,
        val dateOfBirth: Date? = null,
        val exception: CompleteAccountRegistrationScreenException = CompleteAccountRegistrationScreenException()
    )
}
