package com.fitnest.android.screen.private_area.notification

import android.content.Context
import android.text.format.DateUtils
import com.fitnest.android.R
import com.fitnest.android.extension.getHoursDiff
import com.fitnest.android.extension.getMinutesDiff
import com.fitnest.android.extension.isSameHour
import com.fitnest.android.screen.private_area.notification.data.NotificationUIInfo
import com.fitnest.domain.entity.response.NotificationsPageResponse
import com.fitnest.domain.enum.NotificationType
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.*

internal class NotificationsViewMapper(
    private val context: Context
) {

    internal fun mapServerNotificationsToUIModel(notifications: List<NotificationsPageResponse.Notification>?) =
        notifications?.map {
            NotificationUIInfo(
                title = it.title ?: "",
                description = formatNotificationTime(it.date),
                icon = getNotificationIcon(it.type),
                isActive = it.isActive == true
            )
        } ?: emptyList()

    private fun formatNotificationTime(date: LocalDateTime?): String {
        val millis = date?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds() ?: 0

        return if (isSameHour(millis)) {
            val minutesDiff = getMinutesDiff(millis)
            context.getString(R.string.private_area_notifications_minutes_ago, minutesDiff)
        } else if (DateUtils.isToday((millis))) {
            val hoursDiff = getHoursDiff(millis)
            context.getString(R.string.private_area_notifications_hours_ago, hoursDiff)
        } else {
            SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date(millis))
        }
    }

    private fun getNotificationIcon(type: NotificationType?) =
        if (type == NotificationType.WORKOUT) R.drawable.ic_private_area_notification_workout
        else R.drawable.ic_private_area_notification_meal

}