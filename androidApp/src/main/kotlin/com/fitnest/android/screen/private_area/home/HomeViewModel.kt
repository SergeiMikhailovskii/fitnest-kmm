package com.fitnest.android.screen.private_area.home

import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.usecase.privateArea.GetDashboardDataUseCase
import com.fitnest.presentation.base.BaseViewModel
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
        handleRoute(com.fitnest.presentation.navigation.Route.PrivateArea.Notifications)
    }

    internal fun navigateToActivityTracker() {
        handleRoute(com.fitnest.presentation.navigation.Route.PrivateArea.ActivityTracker)
    }

}