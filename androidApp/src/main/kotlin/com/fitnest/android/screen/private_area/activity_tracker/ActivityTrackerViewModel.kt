package com.fitnest.android.screen.private_area.activity_tracker

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class ActivityTrackerViewModel(
    getActivityTrackerPageUseCase: GetActivityTrackerPageUseCase,
    private val viewMapper: ActivityTrackerViewMapper,
    private val deleteActivityUseCase: DeleteActivityUseCase
) : BaseViewModel() {

    private var screenData = ActivityTrackerScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow: StateFlow<ActivityTrackerScreenData> = _screenDataFlow

    init {
        viewModelScope.launch {
            handleProgress(true)
            val response = getActivityTrackerPageUseCase().getOrThrow()
            handleProgress()
            handlePageResponse(response)
        }
    }

    internal fun deleteActivity(activity: ActivityTrackerScreenData.Activity) {
        viewModelScope.launch {
            handleProgress(true)
            val request = viewMapper.mapActivityToDeleteActivityRequest(activity)
            val response = deleteActivityUseCase(request).getOrThrow()
            handlePageResponse(response)
            handleProgress()
        }
    }

    private fun handlePageResponse(widgets: ActivityTrackerPageResponse.ActivityTrackerWidgets?) {
        screenData = viewMapper.mapWidgetsToScreenData(widgets)
        updateScreen()
    }

    private fun updateScreen() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData.copy())
        }
    }
}
