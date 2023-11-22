package com.fitnest.presentation.decompose.registration.steps.completeAccount

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.domain.enum.SexType
import com.fitnest.presentation.decompose.dialog.DialogComponent
import com.fitnest.presentation.decompose.dialog.date.DefaultDateDialogComponent
import com.fitnest.presentation.decompose.registration.steps.completeAccount.anthropometry.DefaultAnthropometryComponent
import com.fitnest.presentation.enumType.CompleteAccountRegistrationScreenBottomSheetType

class DefaultCompleteAccountRegistrationComponent(
    context: ComponentContext
) : CompleteAccountRegistrationComponent,
    ComponentContext by context {

    private val dialogNavigation = SlotNavigation<DialogConfig>()

    private val _model = MutableValue(CompleteAccountRegistrationComponent.Model())
    override val model: Value<CompleteAccountRegistrationComponent.Model> = _model

    override val dialog: Value<ChildSlot<*, DialogComponent>> = childSlot(
        source = dialogNavigation,
        handleBackButton = true
    ) { configuration, componentContext ->
        when (configuration) {
            is DialogConfig.Anthropometry -> DefaultAnthropometryComponent()
            DialogConfig.DateOfBirth -> DefaultDateDialogComponent({
                _model.value = _model.value.copy(dateOfBirth = it)
                dialogNavigation.dismiss()
            }, dialogNavigation::dismiss)
        }
    }

    override fun submitRegistration() {
    }

    override fun setSex(sexType: SexType) {
        _model.value = _model.value.copy(sexType = sexType)
    }

    override fun showDateOfBirthPicker() {
        dialogNavigation.activate(DialogConfig.DateOfBirth)
    }

    override fun openWeightBottomSheet() {
        dialogNavigation.activate(DialogConfig.Anthropometry(CompleteAccountRegistrationScreenBottomSheetType.WEIGHT))
    }

    sealed interface DialogConfig : Parcelable {
        @Parcelize
        data object DateOfBirth : DialogConfig

        @Parcelize
        data class Anthropometry(
            val type: CompleteAccountRegistrationScreenBottomSheetType
        ) : DialogConfig
    }
}
