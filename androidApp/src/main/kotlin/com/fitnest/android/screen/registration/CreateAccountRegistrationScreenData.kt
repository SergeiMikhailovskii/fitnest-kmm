package com.fitnest.android.screen.registration

data class CreateAccountRegistrationScreenData(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var passwordVisible: Boolean = false,
) {

    companion object {
        internal fun init() = CreateAccountRegistrationScreenData()
    }
}
