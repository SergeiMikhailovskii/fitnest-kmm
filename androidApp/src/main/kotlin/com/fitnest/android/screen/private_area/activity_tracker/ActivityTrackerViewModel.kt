package com.fitnest.android.screen.private_area.activity_tracker

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import kotlinx.coroutines.launch

internal class ActivityTrackerViewModel(
    getActivityTrackerPageUseCase: GetActivityTrackerPageUseCase
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            handleProgress(true)
            val response = getActivityTrackerPageUseCase().getOrThrow()
            handleProgress()
        }
    }

}