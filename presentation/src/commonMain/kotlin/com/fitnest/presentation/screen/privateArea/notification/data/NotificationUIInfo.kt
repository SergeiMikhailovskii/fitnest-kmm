package com.fitnest.presentation.screen.privateArea.notification.data

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.datetime.LocalDateTime

data class NotificationUIInfo(
    val id: Int,
    val title: String,
    val description: StringDesc,
    val icon: ImageResource,
    val isActive: Boolean,
    val isPinned: Boolean,
    val date: LocalDateTime? = null
)
