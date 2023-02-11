package com.fitnest.android.screen.private_area.activity_tracker.input

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerViewMapper
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.usecase.private_area.AddActivityUseCase
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
            if (screenData.value == 0) return@launch
            val request = viewMapper.mapActivityInputToRequest(screenData)
            addActivityUseCase(request).getOrThrow()
            handleRoute(Route.DismissBottomSheet)
        }
    }

    private fun updateScreen() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData)
        }
    }
}