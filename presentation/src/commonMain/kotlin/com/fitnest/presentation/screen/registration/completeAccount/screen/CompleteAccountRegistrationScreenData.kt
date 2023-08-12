package com.fitnest.presentation.screen.registration.completeAccount.screen

import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import com.fitnest.domain.extension.dateToString
import com.fitnest.domain.internal.date.Date
import com.fitnest.presentation.enumType.CompleteAccountRegistrationScreenBottomSheetType

data class CompleteAccountRegistrationScreenData(
    val sex: SexType? = null,
    val dateOfBirth: Date? = null,
    val isSexFocused: Boolean = false,
    val weight: Int? = null,
    val height: Int? = null,
    val currentAnthropometryType: CompleteAccountRegistrationScreenBottomSheetType? = null,
    val exception: CompleteAccountRegistrationScreenException = CompleteAccountRegistrationScreenException()
) {

    fun formattedDateOfBirth() = dateOfBirth?.dateToString("dd/MM/yyyy")
}
