package com.fitnest.android.screen.private_area.activity_tracker

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.ErrorColor
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsNormalStyle12Gray1
import com.fitnest.android.style.PoppinsSemiBoldStyle16Black
import com.fitnest.android.style.WhiteColor

@Preview
@Composable
internal fun ActivityProgressBlockPreview() {
    ActivityProgressBlock(modifier = Modifier)
}

@Composable
internal fun ActivityProgressBlock(modifier: Modifier) {
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
            backgroundColor = WhiteColor
        ) {
            Row(modifier = Modifier.padding(Padding.Padding20)) {
                for (i in 0 until 7) {
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
                                color = Color.GRAY
                            })
                        }
                        Text(
                            stringArrayResource(id = R.array.day_names_short)[i],
                            modifier = Modifier.padding(top = Padding.Padding7),
                            style = PoppinsNormalStyle12Gray1
                        )
                    }
                }
            }
        }
    }
}