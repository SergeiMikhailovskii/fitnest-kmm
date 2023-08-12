package com.fitnest.presentation.extension

import com.fitnest.presentation.MR
import dev.icerock.moko.resources.StringResource

val String.errorLocalizedValue: StringResource
    get() = when (this) {
        "error.required" -> MR.strings.error_required
        "first_name.error.invalid" -> MR.strings.first_name_error_invalid
        "last_name.error.invalid" -> MR.strings.last_name_error_invalid
        "email.error.invalid" -> MR.strings.email_error_invalid
        "password.error.invalid" -> MR.strings.password_error_invalid
        "password.error.minLength" -> MR.strings.password_error_minLength
        "error.maxAge" -> MR.strings.error_maxAge
        "error.minAge" -> MR.strings.error_minAge
        "error.unknown" -> MR.strings.error_unknown
        "login.error.invalid" -> MR.strings.login_error_invalid
        "error_server_error" -> MR.strings.error_server_error
        else -> error("unknown error $this")
    }
