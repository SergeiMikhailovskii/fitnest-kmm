package com.fitnest.android.screen.registration.create_account

import com.fitnest.domain.entity.validator.Validator
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException

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

    var exception: CreateAccountRegistrationScreenException = CreateAccountRegistrationScreenException(),

    var validationSchema: Map<String, List<Validator?>>? = null
) {

    /**
     * Create copy function manually as default copy of data class doesn't copy inner objects
     */
    internal fun copy() = CreateAccountRegistrationScreenData(
        this.firstName,
        this.lastName,
        this.email,
        this.password,
        this.passwordVisible,
        this.isFirstNameFocused,
        this.isLastNameFocused,
        this.isEmailFocused,
        this.isPasswordFocused,
        this.exception.copy(),
    )

    companion object {
        internal fun init() = CreateAccountRegistrationScreenData()
    }
}
