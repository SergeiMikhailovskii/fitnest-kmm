package com.fitnest.android.screen.private_area.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.notification.data.NotificationUIInfo
import com.fitnest.android.style.Padding
import com.fitnest.domain.extension.move
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
internal fun NotificationsScreen() {
    val list = mutableListOf<NotificationUIInfo>().apply {
        for (i in 0..150) {
            add(
                NotificationUIInfo(
                    title = "Hey, it’s time for lunch",
                    description = "About $i minutes ago"
                )
            )
        }
    }

    val listState = rememberLazyListState()
    var position by remember { mutableStateOf<Float?>(null) }
    var draggedItem by remember { mutableStateOf<Int?>(null) }

    val indexWithOffset by derivedStateOf {
        draggedItem
            ?.let { listState.layoutInfo.visibleItemsInfo.getOrNull(it - listState.firstVisibleItemIndex) }
            ?.let { Pair(it.index, (position ?: 0f) - it.offset - it.size / 2f) }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .combine(snapshotFlow { position }.distinctUntilChanged()) { state, pos ->
                pos?.let { draggedCenter ->
                    state.visibleItemsInfo.minByOrNull {
                        (draggedCenter - (it.offset + it.size / 2F)).absoluteValue
                    }?.index
                }
            }.distinctUntilChanged()
            .collect { near ->
                draggedItem = when {
                    near == null -> null
                    draggedItem == null -> near
                    else -> near.also { list.move(draggedItem ?: 0, it) }
                }
            }
    }

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(horizontal = Padding.Padding16)
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = { offset ->
                            listState.layoutInfo.visibleItemsInfo
                                .firstOrNull {
                                    offset.y.toInt() in it.offset..it.offset + it.size
                                }
                                ?.also {
                                    position = it.offset + it.size / 2F
                                }
                        },
                        onDrag = { change, dragAmount ->
                            change.consumeAllChanges()
                            position = position?.plus(dragAmount.y)
                        },
                        onDragEnd = {
                            draggedItem = null
                            position = null
                        }
                    )
                }
        ) {
            itemsIndexed(list) { index, it ->
                val offset by remember {
                    derivedStateOf {
                        indexWithOffset?.takeIf { it.first == index }?.second
                    }
                }
                NotificationItem(
                    modifier = Modifier
                        .zIndex(offset?.let { 1f } ?: 0f)
                        .graphicsLayer {
                            translationY = offset ?: 0f
                        },
                    title = it.title,
                    description = it.description,
                    icon = R.drawable.ic_private_area_notification_meal
                )
                Divider()
            }
        }
    }
}