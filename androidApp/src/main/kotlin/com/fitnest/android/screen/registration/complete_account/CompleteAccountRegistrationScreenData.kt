package com.fitnest.android.screen.registration.complete_account

import com.fitnest.domain.enum.SexType

data class CompleteAccountRegistrationScreenData(
    var sex: SexType? = null
) {
    internal fun copy() = CompleteAccountRegistrationScreenData(sex)

    companion object {
        fun init() = CompleteAccountRegistrationScreenData()
    }
}