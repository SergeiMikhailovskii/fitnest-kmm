package com.fitnest.android.screen.private_area.home

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    getDashboardDataUseCase: GetDashboardDataUseCase,
    private val viewMapper: HomeViewMapper
) : BaseViewModel() {

    private var screenData = HomeScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _progressSharedFlow.emit(true)
            val dashboardData = getDashboardDataUseCase().getOrThrow()
            val mappedData = viewMapper.mapDashboardResponseToScreenData(dashboardData)

            screenData = mappedData
            _screenDataFlow.emit(screenData.copy())
            _progressSharedFlow.emit(false)
        }
    }

}