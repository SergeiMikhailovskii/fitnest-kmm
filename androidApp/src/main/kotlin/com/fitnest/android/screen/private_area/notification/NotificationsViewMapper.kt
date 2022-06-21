package com.fitnest.android.screen.private_area.notification

import com.fitnest.android.screen.private_area.notification.data.NotificationUIInfo
import com.fitnest.domain.entity.response.NotificationsPageResponse

internal class NotificationsViewMapper {

    internal fun mapServerNotificationsToUIModel(notifications: List<NotificationsPageResponse.Notification>?) =
        notifications?.map {
            NotificationUIInfo(
                title = it.title ?: "",
                description = it.date.toString()
            )
        } ?: emptyList()

}