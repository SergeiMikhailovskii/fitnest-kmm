package com.fitnest.android.screen.private_area.settings

import android.content.Context
import com.fitnest.android.R
import com.fitnest.presentation.R as MR
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.domain.enum.GoalType
import com.fitnest.domain.usecase.privateArea.GetProfilePageUseCase

internal class SettingsViewMapper(private val context: Context) {
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

    private fun getLocalizedProgram(program: GoalType?): String {
        val programName = when (program) {
            GoalType.IMPROVE_SHAPE -> context.getString(MR.string.registration_goal_improve_shape_title)
            GoalType.LEAN_TONE -> context.getString(MR.string.registration_goal_improve_lean_tone_title)
            GoalType.LOSE_FAT -> context.getString(MR.string.registration_goal_improve_lose_fat_title)
            null -> null
        }

        return programName?.let {
            context.getString(
                R.string.private_area_profile_screen_program_description,
                it
            )
        }.orEmpty()
    }
}