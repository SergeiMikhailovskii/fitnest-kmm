package com.fitnest.android.screen.private_area.notification

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.extension.vibrate
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.style.Padding
import com.fitnest.domain.extension.move
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import kotlin.math.absoluteValue

@Composable
internal fun NotificationsScreen() = subDI(diBuilder = {
    import(PrivateAreaModule.notificationsPrivateAreaModule)
}) {
    val di = localDI()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance { di }
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = NotificationsViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()

    val listState = rememberLazyListState()
    var position by remember { mutableStateOf<Float?>(null) }
    var draggedItem by remember { mutableStateOf<Int?>(null) }

    val progress by viewModel.progressSharedFlow.collectAsState(false)

    val view = LocalView.current

    val indexWithOffset by remember {
        derivedStateOf {
            draggedItem
                ?.let { listState.layoutInfo.visibleItemsInfo.getOrNull(it - listState.firstVisibleItemIndex) }
                ?.let { Pair(it.index, (position ?: 0f) - it.offset - it.size / 2f) }
        }
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
                    else -> near.also {
                        val movedList = screenData.notifications.toMutableList().apply {
                            move(draggedItem ?: 0, it)
                        }.toList()
                        viewModel.updateNotifications(movedList)

                        vibrate(view)
                    }
                }
            }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
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
                            change.consume()
                            position = position?.plus(dragAmount.y)
                        },
                        onDragEnd = {
                            draggedItem = null
                            position = null
                        }
                    )
                }
        ) {
            itemsIndexed(screenData.notifications) { index, it ->
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
                    icon = it.icon,
                    isActive = it.isActive,
                    isPinned = it.isPinned,
                    onPin = {
                        viewModel.pinNotification(screenData.notifications[index].id)
                    },
                    onDelete = {
                        viewModel.deleteNotification(screenData.notifications[index].id)
                    }
                )
                if (screenData.notifications.last() != it) {
                    Divider()
                }
            }
        }
    }

    if (progress) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
