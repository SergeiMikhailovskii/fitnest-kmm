package com.fitnest.android.screen.private_area.notification

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.extension.vibrate
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import kotlin.math.absoluteValue

@Preview
@Composable
private fun NotificationItemPreview() {
    NotificationItem(
        title = "Hey, it’s time for lunch",
        description = "About 1 minutes ago",
        icon = R.drawable.ic_private_area_notification_meal,
        isActive = true,
        isPinned = true
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun NotificationItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    @DrawableRes icon: Int,
    isActive: Boolean,
    isPinned: Boolean,
    onPin: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null
) {
    val view = LocalView.current
    val pinIcon = if (isPinned) Icons.Outlined.PushPin else Icons.Default.PushPin

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                onPin?.invoke()
                vibrate(view)
            } else if (it == DismissValue.DismissedToStart) {
                onDelete?.invoke()
                vibrate(view)
            }
            it == DismissValue.Default
        }
    )
    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.Transparent
                    DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.primary
                    DismissValue.DismissedToStart -> MaterialTheme.colorScheme.error
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val actionIcon = when (direction) {
                DismissDirection.StartToEnd -> pinIcon
                DismissDirection.EndToStart -> Icons.Default.Delete
            }
            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )

            if (dismissState.offset.value.absoluteValue.toInt().pxToDp() > Padding.Padding16) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(horizontal = Padding.Padding16),
                    contentAlignment = alignment
                ) {
                    Icon(
                        actionIcon,
                        contentDescription = null,
                        modifier = Modifier.scale(scale)
                    )
                }
            }
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = Padding.Padding16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isActive) {
                Box(
                    modifier = Modifier
                        .padding(end = Padding.Padding16)
                        .background(
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .size(Dimen.Dimen8)
                )
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.Padding10)
                    .fillMaxHeight()
                    .weight(1F),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(title, style = MaterialTheme.typography.bodySmall)
                Text(
                    description, style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
            if (isPinned) Icon(
                Icons.Default.PushPin,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = Padding.Padding16)
                    .size(Dimen.Dimen12)
            )
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
