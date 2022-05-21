package com.fitnest.android.screen.private_area.home

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            val dashboardData = getDashboardDataUseCase()
            println()
        }
    }

}