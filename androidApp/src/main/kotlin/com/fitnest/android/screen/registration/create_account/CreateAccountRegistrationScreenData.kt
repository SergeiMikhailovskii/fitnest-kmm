package com.fitnest.android.screen.registration.create_account

import com.fitnest.domain.entity.validator.Validator

data class CreateAccountRegistrationScreenData(
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = "",
    var password: String? = "",

    var passwordVisible: Boolean = false,

    var isFirstNameFocused: Boolean = false,
    var isLastNameFocused: Boolean = false,
    var isEmailFocused: Boolean = false,
    var isPasswordFocused: Boolean = false,

    var validationSchema: Map<String, List<Validator?>>? = null
) {

    companion object {
        internal fun init() = CreateAccountRegistrationScreenData()
    }
}
