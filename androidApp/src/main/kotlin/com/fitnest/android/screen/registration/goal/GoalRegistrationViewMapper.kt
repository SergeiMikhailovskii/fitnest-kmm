package com.fitnest.android.screen.registration.goal

import com.fitnest.android.R
import com.fitnest.android.screen.registration.goal.data.GoalPageUIModel

class GoalRegistrationViewMapper {

    internal fun mapGoalIndexToUIModel(index: Int): GoalPageUIModel {
        val title = when (index) {
            0 -> R.string.registration_goal_improve_shape_title
            1 -> R.string.registration_goal_improve_lean_tone_title
            2 -> R.string.registration_goal_improve_lose_fat_title
            else -> throw RuntimeException("unimplemented index=$index handler")
        }
        val description = when (index) {
            0 -> R.string.registration_goal_improve_shape_description
            1 -> R.string.registration_goal_improve_lean_tone_description
            2 -> R.string.registration_goal_improve_lose_fat_description
            else -> throw RuntimeException("unimplemented index=$index handler")
        }
        val image = when (index) {
            0 -> R.drawable.ic_registration_goal_improve_shape
            1 -> R.drawable.ic_registration_goal_lean_tone
            2 -> R.drawable.ic_registration_goal_lose_fat
            else -> throw RuntimeException("unimplemented index=$index handler")
        }

        return GoalPageUIModel(image, title, description)
    }

}