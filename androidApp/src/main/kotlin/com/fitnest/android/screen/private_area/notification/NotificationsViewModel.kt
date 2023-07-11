package com.fitnest.android.screen.private_area.notification

import com.fitnest.android.screen.private_area.notification.data.NotificationScreenData
import com.fitnest.android.screen.private_area.notification.data.NotificationUIInfo
import com.fitnest.domain.entity.response.NotificationsPageResponse
import com.fitnest.domain.usecase.privateArea.DeactivateNotificationsUseCase
import com.fitnest.domain.usecase.privateArea.DeleteNotificationUseCase
import com.fitnest.domain.usecase.privateArea.GetNotificationsPageUseCase
import com.fitnest.domain.usecase.privateArea.PinNotificationUseCase
import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class NotificationsViewModel(
    private val getNotificationsPageUseCase: GetNotificationsPageUseCase,
    private val deactivateNotificationsUseCase: DeactivateNotificationsUseCase,
    private val pinNotificationUseCase: PinNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    private val viewMapper: NotificationsViewMapper
) : BaseViewModel() {

    private var screenData = NotificationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow: StateFlow<NotificationScreenData> = _screenDataFlow

    init {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val result = getNotificationsPageUseCase().getOrThrow()
            handleProgress()
            val mappedNotifications = viewMapper.mapServerNotificationsToUIModel(result)
            screenData = screenData.copy(notifications = mappedNotifications)
            orderNotificationsAndUpdateScreen()

            deactivateNotifications(result)
        }
    }

    internal fun updateNotifications(movedList: List<NotificationUIInfo>) {
        screenData = screenData.copy(notifications = movedList)
        updateScreen()
    }

    internal fun pinNotification(id: Int) {
        val notificationToPin = screenData.notifications.firstOrNull { it.id == id } ?: return
        val request = viewMapper.mapNotificationToPinRequest(notificationToPin)

        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            pinNotificationUseCase(request).getOrThrow()
            handleProgress()
            screenData = screenData.copy(
                notifications = screenData.notifications.map {
                    if (it.id == notificationToPin.id) {
                        it.copy(isPinned = !it.isPinned)
                    } else {
                        it
                    }
                }
            )
            orderNotificationsAndUpdateScreen()
        }
    }

    internal fun deleteNotification(id: Int) {
        val notificationToDelete = screenData.notifications.firstOrNull { it.id == id } ?: return
        val request = viewMapper.mapNotificationToDeleteRequest(notificationToDelete)

        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            deleteNotificationUseCase(request).getOrThrow()
            handleProgress()
            screenData = screenData.copy(
                notifications = screenData.notifications.filter { it.id != id }
            )
            orderNotificationsAndUpdateScreen()
        }
    }

    private fun orderNotificationsAndUpdateScreen() {
        val groupedNotifications = screenData.notifications.sortedByDescending { it.isPinned }
            .groupBy {
                it.isPinned
            }.mapValues {
                it.value.sortedByDescending { it.date }
            }
        val orderedNotifications = mutableListOf<NotificationUIInfo>().apply {
            groupedNotifications.forEach {
                addAll(it.value)
            }
        }
        screenData = screenData.copy(notifications = orderedNotifications)
        updateScreen()
    }

    private fun deactivateNotifications(notifications: List<NotificationsPageResponse.Notification>?) {
        val activeNotificationIds = notifications?.filter { it.isActive == true }
            ?.mapNotNull { it.id }

        if (!activeNotificationIds.isNullOrEmpty()) {
            viewModelScope.launch(exceptionHandler) { deactivateNotificationsUseCase(activeNotificationIds) }
        }
    }

    private fun updateScreen() {
        viewModelScope.launch(exceptionHandler) {
            _screenDataFlow.emit(screenData.copy())
        }
    }

}