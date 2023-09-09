package com.fitnest.android.screen.private_area.activity_tracker.composable

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.R
import com.fitnest.presentation.screen.privateArea.activityTracker.data.ActivityTrackerScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun ActivityProgressBlock(
    modifier: Modifier,
    sections: ImmutableList<ActivityTrackerScreenData.Progress>?,
    progress: Boolean
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.private_area_activity_tracker_screen_activity_progress_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.placeholder(progress, highlight = PlaceholderHighlight.fade())
        )
        Card(
            modifier = Modifier
                .padding(top = Padding.Padding15)
                .fillMaxWidth()
                .placeholder(
                    progress,
                    highlight = PlaceholderHighlight.fade(),
                    shape = RoundedCornerShape(Dimen.Dimen20)
                )
                .run {
                    if (progress) {
                        height(Dimen.Dimen160)
                    } else this
                },
            shape = RoundedCornerShape(Dimen.Dimen20),
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(Padding.Padding20)
            ) {
                sections?.forEach {
                    Column(
                        modifier = Modifier.weight(1F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val pathColor = MaterialTheme.colorScheme.surfaceVariant.toArgb()
                        Canvas(modifier = Modifier.height(Dimen.Dimen135)) {
                            val corners = floatArrayOf(
                                Dimen.Dimen10.toPx(), Dimen.Dimen10.toPx(),
                                Dimen.Dimen10.toPx(), Dimen.Dimen10.toPx(),
                                Dimen.Dimen10.toPx(), Dimen.Dimen10.toPx(),
                                Dimen.Dimen10.toPx(), Dimen.Dimen10.toPx()
                            )
                            val path = Path()
                            path.addRoundRect(
                                RectF(
                                    -Dimen.Dimen10.toPx(),
                                    0F,
                                    Dimen.Dimen10.toPx(),
                                    Dimen.Dimen135.toPx()
                                ),
                                corners,
                                Path.Direction.CW
                            )
                            drawContext.canvas.nativeCanvas.drawPath(path, Paint().apply {
                                color = pathColor
                            })

                            val newPath = Path()
                            newPath.addRoundRect(
                                RectF(
                                    -Dimen.Dimen10.toPx(),
                                    Dimen.Dimen135.toPx() - Dimen.Dimen135.toPx() * it.progress,
                                    Dimen.Dimen10.toPx(),
                                    Dimen.Dimen135.toPx()
                                ),
                                corners,
                                Path.Direction.CW
                            )
                            drawContext.canvas.nativeCanvas.drawPath(newPath, Paint().apply {
                                it.color?.let(::setColor)
                            })
                        }
                        Text(
                            text = dev.icerock.moko.resources.compose.stringResource(it.day),
                            modifier = Modifier.padding(top = Padding.Padding7),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    }
}
