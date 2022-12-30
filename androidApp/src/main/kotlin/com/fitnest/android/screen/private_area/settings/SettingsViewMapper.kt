package com.fitnest.android.screen.private_area.settings

import android.content.Context
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.domain.entity.response.ProfilePageResponse
import com.fitnest.domain.enum.GoalType

internal class SettingsViewMapper(private val context: Context) {
    fun mapProfileWidgetIntoScreenData(
        widget: ProfilePageResponse.ProfileInfoWidget
    ) = SettingsScreenData(
        name = "${widget.firstName} ${widget.lastName}",
        program = getLocalizedProgram(widget.program),
        height = widget.height,
        weight = widget.weight,
        age = widget.age
    )

    private fun getLocalizedProgram(program: GoalType?): String {
        val programName = when (program) {
            GoalType.IMPROVE_SHAPE -> context.getString(R.string.registration_goal_improve_shape_title)
            GoalType.LEAN_TONE -> context.getString(R.string.registration_goal_improve_lean_tone_title)
            GoalType.LOSE_FAT -> context.getString(R.string.registration_goal_improve_lose_fat_title)
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