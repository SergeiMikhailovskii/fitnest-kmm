package com.fitnest.presentation.screen.privateArea.settings.data

import dev.icerock.moko.resources.StringResource

data class SettingsScreenData(
    val name: String? = null,
    val program: StringResource? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null,
    val areNotificationsEnabled: Boolean = false
)
