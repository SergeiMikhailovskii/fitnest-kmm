package com.fitnest.android.extension.enum

import android.content.Context
import com.fitnest.domain.enum.SexType
import com.fitnest.presentation.R

val SexType.localizedNameId: Int
    get() = when (this) {
        SexType.MALE -> R.string.registration_complete_account_sex_male
        SexType.FEMALE -> R.string.registration_complete_account_sex_female
    }

fun SexType.Companion.localizedNames(context: Context) =
    SexType.values().map { context.getString(it.localizedNameId) }

fun SexType.Companion.fromLocalizedName(sex: String, context: Context) =
    if (context.getString(SexType.MALE.localizedNameId) == sex) SexType.MALE else SexType.FEMALE