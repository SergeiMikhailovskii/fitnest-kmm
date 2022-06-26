package com.fitnest.android.screen.private_area.notification

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.style.*
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@Preview
@Composable
private fun NotificationItemPreview() {
    NotificationItem(
        title = "Hey, it’s time for lunch",
        description = "About 1 minutes ago",
        icon = R.drawable.ic_private_area_notification_meal,
        isActive = true
    )
}

@ExperimentalMaterialApi
@Composable
internal fun NotificationItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    @DrawableRes icon: Int,
    isActive: Boolean
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                println("dismissed to end")
            } else if (it == DismissValue.DismissedToStart) {
                println("dismissed to start")
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
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> Color.Red
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val actionIcon = when (direction) {
                DismissDirection.StartToEnd -> Icons.Default.PushPin
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
                Text(title, style = PoppinsMediumStyle12Black)
                Text(description, style = PoppinsMediumStyle10Gray1)
            }
            Icon(Icons.Default.MoreVert, contentDescription = null, tint = GrayColor2)
        }
    }
}
