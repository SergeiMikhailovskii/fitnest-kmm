package com.fitnest.presentation.screen.privateArea.notification

import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.entity.response.NotificationsPageResponse
import com.fitnest.domain.enum.NotificationType
import com.fitnest.domain.extension.dateToString
import com.fitnest.domain.extension.isToday
import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.internal.date.setTimeInMs
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.getHoursDiff
import com.fitnest.presentation.extension.getMinutesDiff
import com.fitnest.presentation.extension.isSameHour
import com.fitnest.presentation.screen.privateArea.notification.data.NotificationUIInfo
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class NotificationsViewMapper {

    internal fun mapServerNotificationsToUIModel(notifications: List<NotificationsPageResponse.Notification>?) =
        notifications?.map {
            NotificationUIInfo(
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                description = formatNotificationTime(it.date),
                icon = getNotificationIcon(it.type),
                isActive = it.isActive == true,
                isPinned = it.isPinned == true,
                date = it.date
            )
        } ?: emptyList()

    internal fun mapNotificationToPinRequest(notification: NotificationUIInfo) =
        PinNotificationRequest(notification.id, !notification.isPinned)

    internal fun mapNotificationToDeleteRequest(notification: NotificationUIInfo) =
        DeleteNotificationRequest(notification.id)

    private fun formatNotificationTime(date: LocalDateTime?): StringDesc {
        val millis = date?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds() ?: 0

        return if (isSameHour(millis)) {
            val minutesDiff = getMinutesDiff(millis)
            MR.strings.private_area_notifications_minutes_ago.format(minutesDiff)
        } else if (millis.isToday) {
            val hoursDiff = getHoursDiff(millis)
            MR.strings.private_area_notifications_hours_ago.format(hoursDiff)
        } else {
            StringDesc.Raw(Date().apply { setTimeInMs(millis) }.dateToString("dd MMMM"))
        }
    }

    private fun getNotificationIcon(type: NotificationType?) =
        if (type == NotificationType.WORKOUT) {
            MR.images.ic_private_area_notification_workout
        } else {
            MR.images.ic_private_area_notification_meal
        }
}
