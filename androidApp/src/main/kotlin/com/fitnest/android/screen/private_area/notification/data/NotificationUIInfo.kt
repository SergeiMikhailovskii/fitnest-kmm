package com.fitnest.android.screen.private_area.notification.data

import androidx.annotation.DrawableRes
import kotlinx.datetime.LocalDateTime

internal data class NotificationUIInfo(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val icon: Int,
    val isActive: Boolean,
    val isPinned: Boolean,
    val date: LocalDateTime? = null
)
