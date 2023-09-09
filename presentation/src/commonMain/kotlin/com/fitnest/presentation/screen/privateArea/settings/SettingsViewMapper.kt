package com.fitnest.presentation.screen.privateArea.settings

import com.fitnest.domain.enum.GoalType
import com.fitnest.domain.usecase.privateArea.GetProfilePageUseCase
import com.fitnest.presentation.MR
import com.fitnest.presentation.screen.privateArea.settings.data.SettingsScreenData

class SettingsViewMapper {
    fun mapProfileModelIntoScreenData(
        model: GetProfilePageUseCase.Model
    ) = SettingsScreenData(
        name = "${model.widget?.firstName} ${model.widget?.lastName}",
        program = getLocalizedProgram(model.widget?.program),
        height = model.widget?.height,
        weight = model.widget?.weight,
        age = model.widget?.age,
        areNotificationsEnabled = model.areNotificationsEnabled
    )

    private fun getLocalizedProgram(program: GoalType?) = when (program) {
        GoalType.IMPROVE_SHAPE -> MR.strings.registration_goal_improve_shape_title
        GoalType.LEAN_TONE -> MR.strings.registration_goal_improve_lean_tone_title
        GoalType.LOSE_FAT -> MR.strings.registration_goal_improve_lose_fat_title
        null -> null
    }
}
