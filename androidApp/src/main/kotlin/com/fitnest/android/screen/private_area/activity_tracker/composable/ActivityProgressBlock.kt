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
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Preview
@Composable
internal fun ActivityProgressBlockPreview() {
    ActivityProgressBlock(
        modifier = Modifier, sections = persistentListOf(
            ActivityTrackerScreenData.Progress(
                day = "Mon",
                progress = 0.1F,
                color = MaterialTheme.colorScheme.primary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Tue",
                progress = 0.2F,
                color = MaterialTheme.colorScheme.tertiary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Wed",
                progress = 0.3F,
                color = MaterialTheme.colorScheme.primary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Thu",
                progress = 0.4F,
                color = MaterialTheme.colorScheme.tertiary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Fri",
                progress = 0.5F,
                color = MaterialTheme.colorScheme.primary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Sat",
                progress = 0.6F,
                color = MaterialTheme.colorScheme.tertiary.toArgb()
            ),
            ActivityTrackerScreenData.Progress(
                day = "Sun",
                progress = 0.7F,
                color = MaterialTheme.colorScheme.primary.toArgb()
            )
        )
    )
}

@Composable
internal fun ActivityProgressBlock(
    modifier: Modifier,
    sections: ImmutableList<ActivityTrackerScreenData.Progress>?
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.private_area_activity_tracker_screen_activity_progress_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Card(
            modifier = Modifier
                .padding(top = Padding.Padding15)
                .fillMaxWidth(),
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
                            text = it.day,
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
