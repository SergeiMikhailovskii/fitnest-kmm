package com.fitnest.android.screen.registration.complete_account

import com.fitnest.domain.enum.SexType
import java.text.SimpleDateFormat
import java.util.*

data class CompleteAccountRegistrationScreenData(
    var sex: SexType? = null,
    var dateOfBirth: Date? = null,
    var isSexFocused: Boolean = false,
    var weight: Int? = null,
    var height: Int? = null,
) {
    internal fun copy() =
        CompleteAccountRegistrationScreenData(sex, dateOfBirth, isSexFocused, weight, height)

    internal fun formattedDateOfBirth() =
        dateOfBirth?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }

    companion object {
        fun init() = CompleteAccountRegistrationScreenData()
    }
}