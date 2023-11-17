package com.fitnest.presentation.extension.enumType

import com.fitnest.domain.enum.SexType
import com.fitnest.presentation.MR
import dev.icerock.moko.resources.StringResource

val SexType.stringResource: StringResource
    get() = when (this) {
        SexType.MALE -> MR.strings.registration_complete_account_sex_male
        SexType.FEMALE -> MR.strings.registration_complete_account_sex_female
    }

val SexType.Companion.stringResources: List<StringResource>
    get() = SexType.entries.map { it.stringResource }

fun SexType.Companion.fromStringResource(resource: StringResource) = when (resource) {
    MR.strings.registration_complete_account_sex_male -> SexType.MALE
    MR.strings.registration_complete_account_sex_female -> SexType.FEMALE
    else -> error("Unknown string resource - $resource")
}
