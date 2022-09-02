package com.fitnest.android.screen.private_area.activity_tracker

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import kotlinx.coroutines.launch

internal class ActivityTrackerViewModel(
    getActivityTrackerPageUseCase: GetActivityTrackerPageUseCase,
    private val viewMapper: ActivityTrackerViewMapper
) : BaseViewModel() {

    private var screenData = ActivityTrackerScreenData()

    init {
        viewModelScope.launch {
            handleProgress(true)
            val response = getActivityTrackerPageUseCase().getOrThrow()
            handleProgress()
            handlePageResponse(response)
        }
    }

    private fun handlePageResponse(widgets: ActivityTrackerPageResponse.ActivityTrackerWidgets?) {
        screenData = viewMapper.mapWidgetsToScreenData(widgets)
    }
}
