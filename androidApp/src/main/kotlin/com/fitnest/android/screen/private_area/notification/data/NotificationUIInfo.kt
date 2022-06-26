package com.fitnest.android.screen.private_area.notification.data

import androidx.annotation.DrawableRes

data class NotificationUIInfo(
    val title: String,
    val description: String,
    @DrawableRes val icon: Int
)
