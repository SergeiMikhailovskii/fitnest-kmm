package com.fitnest.android.screen.private_area.activity_tracker.input

import com.fitnest.presentation.screen.privateArea.activityTracker.ActivityTrackerViewMapper
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.usecase.privateArea.AddActivityUseCase
import com.fitnest.presentation.base.BaseViewModel
import com.fitnest.presentation.screen.privateArea.activityTracker.input.ActivityInputScreenData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ActivityInputViewModel(
    private val viewMapper: ActivityTrackerViewMapper,
    private val addActivityUseCase: AddActivityUseCase
) : BaseViewModel() {

    private var screenData = ActivityInputScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData)
    val screenDataFlow = _screenDataFlow.asStateFlow()

    fun setCurrentActiveTab(tab: ActivityType) {
        screenData = screenData.copy(activityType = tab)
        updateScreen()
    }

    fun setValue(value: Int) {
        screenData = screenData.copy(value = value)
    }

    fun submitActivity() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            if (screenData.value == 0) {
                handleProgress(false)
                handleRoute(com.fitnest.presentation.navigation.Route.DismissBottomSheet)
            } else {
                val request = viewMapper.mapActivityInputToRequest(screenData)
                addActivityUseCase(request).getOrThrow()
                handleProgress(false)
                handleRoute(com.fitnest.presentation.navigation.Route.DismissBottomSheet)
            }
        }
    }

    private fun updateScreen() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData)
        }
    }
}