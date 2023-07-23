package com.fitnest.presentation.screen.registration.goal

import com.fitnest.domain.enum.GoalType
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.presentation.base.BaseViewModel
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.goal.data.GoalRegistrationScreenData
import kotlinx.coroutines.launch

class GoalRegistrationViewModel(
    private val viewMapper: GoalRegistrationViewMapper,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase
) : BaseViewModel() {

    private var screenData = GoalRegistrationScreenData()

    fun setGoal(goal: GoalType) {
        screenData = screenData.copy(goalType = goal)
    }

    fun submitRegistration() {
        val requestData = viewMapper.mapScreenDataToStepRequestModel(screenData)

        viewModelScope.launch(exceptionHandler) {
            val response = submitRegistrationStepAndGetNextUseCase(requestData).getOrThrow()
            response.step?.let { handleRoute(Route.Registration.Step(it)) }
        }
    }

}