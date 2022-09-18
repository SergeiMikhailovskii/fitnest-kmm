package com.fitnest.android.screen.registration.goal

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.goal.data.GoalRegistrationScreenData
import com.fitnest.domain.enum.GoalType
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
import kotlinx.coroutines.launch

class GoalRegistrationViewModel(
    private val viewMapper: GoalRegistrationViewMapper,
    private val submitRegistrationStepAndGetNext: SubmitRegistrationStepAndGetNext
) : BaseViewModel() {

    private var screenData = GoalRegistrationScreenData()

    internal fun setGoal(goal: GoalType) {
        screenData = screenData.copy(goalType = goal)
    }

    internal fun submitRegistration() {
        val requestData = viewMapper.mapScreenDataToStepRequestModel(screenData)

        viewModelScope.launch {
            val response = submitRegistrationStepAndGetNext(requestData).getOrThrow()
            response.step?.let { handleRoute(Route.RegistrationStep(it)) }
        }
    }

}