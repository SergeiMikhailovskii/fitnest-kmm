package com.fitnest.android.screen.private_area.activity_tracker

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class ActivityTrackerViewModel(
    private val getActivityTrackerPageUseCase: GetActivityTrackerPageUseCase,
    private val viewMapper: ActivityTrackerViewMapper,
    private val deleteActivityUseCase: DeleteActivityUseCase
) : BaseViewModel() {

    private var screenData = ActivityTrackerScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    val screenDataFlow: StateFlow<ActivityTrackerScreenData> = _screenDataFlow

    fun getInitialInfo() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val response = getActivityTrackerPageUseCase().getOrThrow()
            handleProgress()
            handlePageResponse(response)
        }
    }

    fun deleteActivity(activity: ActivityTrackerScreenData.Activity) {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val request = viewMapper.mapActivityToDeleteActivityRequest(activity)
            val response = deleteActivityUseCase(request).getOrThrow()
            handlePageResponse(response)
            handleProgress()
        }
    }

    fun openActivityInputBottomSheet() {
        handleRoute(Route.PrivateArea.Tracker.ActivityInputBottomSheet)
    }

    private fun handlePageResponse(widgets: ActivityTrackerPageResponse.ActivityTrackerWidgets?) {
        screenData = viewMapper.mapWidgetsToScreenData(widgets)
        updateScreen()
    }

    private fun updateScreen() {
        viewModelScope.launch(exceptionHandler) {
            _screenDataFlow.emit(screenData.copy())
        }
    }
}
