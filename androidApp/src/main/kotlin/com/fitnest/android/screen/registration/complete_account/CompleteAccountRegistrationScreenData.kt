package com.fitnest.android.screen.registration.complete_account

import com.fitnest.android.extension.format
import com.fitnest.domain.entity.validator.Validator
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import java.util.*

data class CompleteAccountRegistrationScreenData(
    var sex: SexType? = null,
    var dateOfBirth: Date? = null,
    var isSexFocused: Boolean = false,
    var weight: Int? = null,
    var height: Int? = null,
    var exception: CompleteAccountRegistrationScreenException = CompleteAccountRegistrationScreenException(),
    var validationSchema: Map<String, List<Validator?>>? = null
) {
    internal fun copy() =
        CompleteAccountRegistrationScreenData(
            sex,
            dateOfBirth,
            isSexFocused,
            weight,
            height,
            exception.copy()
        )

    internal fun formattedDateOfBirth() = dateOfBirth?.format("dd/MM/yyyy")

    companion object {
        fun init() = CompleteAccountRegistrationScreenData()
    }
}