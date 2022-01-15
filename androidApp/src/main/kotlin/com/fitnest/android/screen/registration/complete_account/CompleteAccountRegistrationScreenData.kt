package com.fitnest.android.screen.registration.complete_account

import com.fitnest.domain.enum.SexType

data class CompleteAccountRegistrationScreenData(
    var sex: SexType? = null,
    var isSexFocused: Boolean = false
) {
    internal fun copy() = CompleteAccountRegistrationScreenData(sex, isSexFocused)

    companion object {
        fun init() = CompleteAccountRegistrationScreenData()
    }
}