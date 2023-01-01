package com.fitnest.android.screen.private_area.home

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.usecase.private_area.GetDashboardDataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(
    getDashboardDataUseCase: GetDashboardDataUseCase,
    private val viewMapper: HomeViewMapper
) : BaseViewModel() {

    private var screenData = HomeScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)

            delay(10000)

            val dashboardData = getDashboardDataUseCase().getOrThrow()
            dashboardData?.let {
                val mappedData = viewMapper.mapDashboardResponseToScreenData(it)
                screenData = mappedData
                _screenDataFlow.emit(screenData.copy())
            }

            handleProgress(false)
        }
    }

    internal fun navigateToNotifications() {
        handleRoute(Route.PrivateAreaNotifications)
    }

    internal fun navigateToActivityTracker() {
        handleRoute(Route.PrivateAreaActivityTracker)
    }

}