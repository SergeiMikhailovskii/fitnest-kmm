package com.fitnest.domain.entity.response

import com.fitnest.domain.enum.NotificationType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NotificationsPageResponse(
    val widgets: NotificationsWidgets? = null
) {

    @Serializable
    class NotificationsWidgets(
        @SerialName("NOTIFICATIONS_WIDGET")
        val notificationsWidget: NotificationsWidget? = null
    )

    @Serializable
    class NotificationsWidget(
        val notifications: List<Notification>? = null
    )

    @Serializable
    class Notification(
        val id: Int? = null,
        val title: String? = null,
        val date: LocalDateTime? = null,
        val type: NotificationType? = null,
        @SerialName("is_active")
        val isActive: Boolean? = null,
        @SerialName("is_pinned")
        val isPinned: Boolean? = null
    )
}
