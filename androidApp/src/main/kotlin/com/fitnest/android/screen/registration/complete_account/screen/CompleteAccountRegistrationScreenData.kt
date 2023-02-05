package com.fitnest.android.screen.registration.complete_account.screen

import com.fitnest.android.extension.format
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import java.util.Date

data class CompleteAccountRegistrationScreenData(
    val sex: SexType? = null,
    val dateOfBirth: Date? = null,
    val isSexFocused: Boolean = false,
    val weight: Int? = null,
    val height: Int? = null,
    val currentAnthropometryType: CompleteAccountRegistrationScreenBottomSheetType? = null,
    val exception: CompleteAccountRegistrationScreenException = CompleteAccountRegistrationScreenException()
) {

    internal fun formattedDateOfBirth() = dateOfBirth?.format("dd/MM/yyyy")
}
