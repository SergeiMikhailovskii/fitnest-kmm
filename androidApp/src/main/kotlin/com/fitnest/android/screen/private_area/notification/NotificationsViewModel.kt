package com.fitnest.android.screen.private_area.notification

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.private_area.notification.data.NotificationScreenData
import com.fitnest.android.screen.private_area.notification.data.NotificationUIInfo
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

internal class NotificationsViewModel(
    private val getNotificationsPageUseCase: GetNotificationsPageUseCase,
    private val viewMapper: NotificationsViewMapper
) : BaseViewModel() {

    private var screenData = NotificationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow: StateFlow<NotificationScreenData> = _screenDataFlow

    init {
        viewModelScope.launch {
            val result = getNotificationsPageUseCase().getOrThrow()
            val mappedNotifications = viewMapper.mapServerNotificationsToUIModel(result)
            screenData = screenData.copy(notifications = mappedNotifications)
            updateScreen()
        }
    }

    internal fun updateNotifications(movedList: List<NotificationUIInfo>) {
        screenData = screenData.copy(notifications = movedList)
        updateScreen()
    }

    private fun updateScreen() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData.copy())
        }
    }

}