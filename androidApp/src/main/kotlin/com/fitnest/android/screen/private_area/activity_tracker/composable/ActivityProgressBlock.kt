package com.fitnest.android.screen.private_area.activity_tracker

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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityProgressSectionData
import com.fitnest.android.style.BorderColor
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsNormalStyle12Gray1
import com.fitnest.android.style.PoppinsSemiBoldStyle16Black
import com.fitnest.android.style.SecondaryColor
import com.fitnest.android.style.WhiteColor

@Preview
@Composable
internal fun ActivityProgressBlockPreview() {
    ActivityProgressBlock(
        modifier = Modifier, sections = arrayOf(
            ActivityProgressSectionData(
                dayName = "Mon",
                progress = 0.1F,
                color = BrandColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Tue",
                progress = 0.2F,
                color = SecondaryColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Wed",
                progress = 0.3F,
                color = BrandColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Thu",
                progress = 0.4F,
                color = SecondaryColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Fri",
                progress = 0.5F,
                color = BrandColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Sat",
                progress = 0.6F,
                color = SecondaryColor.toArgb()
            ),
            ActivityProgressSectionData(
                dayName = "Sun",
                progress = 0.7F,
                color = BrandColor.toArgb()
            )
        )
    )
}

@Composable
internal fun ActivityProgressBlock(
    modifier: Modifier,
    sections: Array<ActivityProgressSectionData>
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.private_area_activity_tracker_screen_activity_progress_title),
            style = PoppinsSemiBoldStyle16Black
        )
        Card(
            modifier = Modifier
                .padding(top = Padding.Padding15)
                .fillMaxWidth(),
            shape = RoundedCornerShape(Dimen.Dimen20),
        ) {
            Row(
                modifier = Modifier
                    .background(WhiteColor)
                    .padding(Padding.Padding20)
            ) {
                sections.forEach {
                    Column(
                        modifier = Modifier.weight(1F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                                color = BorderColor.toArgb()
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
                                color = it.color
                            })
                        }
                        Text(
                            text = it.dayName,
                            modifier = Modifier.padding(top = Padding.Padding7),
                            style = PoppinsNormalStyle12Gray1
                        )
                    }
                }
            }
        }
    }
}
