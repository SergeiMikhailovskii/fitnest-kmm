package com.fitnest.presentation.screen.registration.goal

import com.fitnest.domain.entity.request.GoalStepRequest
import com.fitnest.domain.enum.GoalType
import com.fitnest.presentation.MR
import com.fitnest.presentation.base.BaseViewMapper
import com.fitnest.presentation.screen.registration.goal.data.GoalPageUIModel
import com.fitnest.presentation.screen.registration.goal.data.GoalRegistrationScreenData

class GoalRegistrationViewMapper : BaseViewMapper<GoalRegistrationScreenData, GoalStepRequest> {

    override fun mapScreenDataToStepRequestModel(data: GoalRegistrationScreenData) =
        GoalStepRequest(goal = data.goalType)

    fun mapGoalIndexToUIModel(index: Int): GoalPageUIModel {
        val title = when (index) {
            0 -> MR.strings.registration_goal_improve_shape_title
            1 -> MR.strings.registration_goal_improve_lean_tone_title
            2 -> MR.strings.registration_goal_improve_lose_fat_title
            else -> throw RuntimeException("unimplemented index=$index handler")
        }
        val description = when (index) {
            0 -> MR.strings.registration_goal_improve_shape_description
            1 -> MR.strings.registration_goal_improve_lean_tone_description
            2 -> MR.strings.registration_goal_improve_lose_fat_description
            else -> throw RuntimeException("unimplemented index=$index handler")
        }
        val image = when (index) {
            0 -> MR.images.ic_registration_goal_improve_shape
            1 -> MR.images.ic_registration_goal_lean_tone
            2 -> MR.images.ic_registration_goal_lose_fat
            else -> throw RuntimeException("unimplemented index=$index handler")
        }

        return GoalPageUIModel(image, title, description)
    }

    fun mapGoalIndexToGoalType(index: Int) = when (index) {
        0 -> GoalType.IMPROVE_SHAPE
        1 -> GoalType.LEAN_TONE
        2 -> GoalType.LOSE_FAT
        else -> throw RuntimeException("unimplemented index=$index handler")
    }
}
