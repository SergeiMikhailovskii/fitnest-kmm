package com.fitnest.presentation.screen.privateArea.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.brandGradient
import com.fitnest.presentation.extension.pxToDp
import com.fitnest.presentation.extension.tertiaryGradient
import com.fitnest.presentation.extension.textBrush
import com.fitnest.presentation.screen.privateArea.home.data.HomeScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.style.TextSize
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
internal fun ActivityStatusBlock(activityStatusWidget: HomeScreenData.ActivityStatusWidget) {
    Column(modifier = Modifier.padding(top = Padding.Padding30)) {
        Text(
            stringResource(MR.strings.private_area_dashboard_activity_status_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        activityStatusWidget.heartRateSubWidget?.let {
            HeartRate(it)
        }

        Row(
            modifier = Modifier
                .padding(top = Padding.Padding16)
                .height(IntrinsicSize.Min)
        ) {
            activityStatusWidget.waterIntakeSubWidget?.let {
                WaterIntakeBlock(modifier = Modifier.weight(1F), waterIntakeSubWidget = it)
            }

            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = Padding.Padding8)
            ) {
                activityStatusWidget.sleepSubWidget?.let {
                    SleepBlock(it)
                }
                activityStatusWidget.caloriesSubWidget?.let {
                    CaloriesBlock(it)
                }
            }
        }
    }
}

@Composable
private fun HeartRate(heartRateSubWidget: HomeScreenData.HeartRateSubWidget) {
    var chartWidth by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding15)
            .background(
                brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient),
                shape = RoundedCornerShape(Dimen.Dimen16),
                alpha = 0.2F
            )
    ) {
        Box {
            Column(
                modifier = Modifier.padding(
                    top = Padding.Padding20,
                    bottom = Padding.Padding30
                )
            ) {
                Text(
                    stringResource(MR.strings.private_area_dashboard_heart_rate_title),
                    modifier = Modifier.padding(start = Padding.Padding20),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    stringResource(
                        MR.strings.private_area_dashboard_heart_rate_bpm,
                        heartRateSubWidget.rate ?: 0
                    ),
                    modifier = Modifier
                        .padding(start = Padding.Padding20, top = Padding.Padding5)
                        .textBrush(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient)),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Image(
                    painter = painterResource(MR.images.ic_private_area_heart_rate_graph),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            chartWidth = it.width
                        },
                    contentScale = ContentScale.FillBounds
                )
            }

//            Column {
//                Box(
//                    modifier = Modifier.padding(
//                        start = (chartWidth * 0.62)
//                            .toInt()
//                            .pxToDp(),
//                        top = Padding.Padding50
//                    )
//                ) {
//                    Column {
//                        val lastHeartRateInstant =
//                            heartRateSubWidget.date?.toInstant(TimeZone.currentSystemDefault())
//                                ?: return
//                        val diff = Clock.System.now() - lastHeartRateInstant
//                        val minutesDiff = diff.inWholeMinutes
//                        DrawTooltip(minutesDiff)
//                    }
//                }

            Box(
                modifier = Modifier.padding(
                    start = (chartWidth * 0.62)
                        .toInt()
                        .pxToDp(),
                    top = Padding.Padding35
                )
            ) {
                DrawActivityRing()
            }
//            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SleepBlock(sleepSubWidget: HomeScreenData.SleepSubWidget) {
    Box(
        modifier = Modifier
            .shadow(elevation = Dimen.Dimen40)
            .clip(RoundedCornerShape(size = Dimen.Dimen20))
            .background(MaterialTheme.colorScheme.onPrimary)
            .aspectRatio(1F)
    ) {
        Column {
            Text(
                stringResource(MR.strings.private_area_dashboard_sleep_title),
                modifier = Modifier.padding(
                    start = Padding.Padding20,
                    top = Padding.Padding20
                ),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                buildSleepDurationAnnotatedString(
                    hours = sleepSubWidget.hours ?: 0,
                    minutes = sleepSubWidget.minutes ?: 0
                ),
                modifier = Modifier
                    .padding(
                        start = Padding.Padding20,
                        top = Padding.Padding5
                    )
                    .textBrush(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient)),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Image(
                painter = org.jetbrains.compose.resources.painterResource("ic_private_area_sleep_graph.xml"),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = Padding.Padding20, vertical = Padding.Padding5)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
private fun buildSleepDurationAnnotatedString(hours: Int, minutes: Int) = buildAnnotatedString {
    val hoursText = stringResource(MR.strings.private_area_dashboard_sleep_hours)
    val minutesText = stringResource(MR.strings.private_area_dashboard_sleep_minutes)
    withStyle(SpanStyle(fontSize = TextSize.Size14)) {
        append(hours.toString())
    }
    withStyle(SpanStyle(fontSize = TextSize.Size10)) {
        append(hoursText)
    }
    withStyle(SpanStyle(fontSize = TextSize.Size14)) {
        append(" $minutes")
    }
    withStyle(SpanStyle(fontSize = TextSize.Size10)) {
        append(minutesText)
    }
}

@Composable
private fun CaloriesBlock(caloriesSubWidget: HomeScreenData.CaloriesSubWidget) {
    Box(
        modifier = Modifier
            .padding(top = Padding.Padding15)
            .fillMaxWidth()
            .shadow(elevation = Dimen.Dimen40)
            .clip(RoundedCornerShape(size = Dimen.Dimen20))
            .background(MaterialTheme.colorScheme.onPrimary)
            .aspectRatio(1F)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(MR.strings.private_area_dashboard_calories_title),
                modifier = Modifier
                    .padding(
                        start = Padding.Padding20,
                        top = Padding.Padding20
                    )
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                stringResource(
                    MR.strings.private_area_dashboard_calories_value,
                    caloriesSubWidget.consumed ?: 0
                ),
                modifier = Modifier
                    .padding(
                        start = Padding.Padding20,
                        top = Padding.Padding5
                    )
                    .textBrush(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient))
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Box(
                modifier = Modifier
                    .padding(bottom = Padding.Padding10)
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(180F),
                    progress = (
                        (
                            caloriesSubWidget.consumed?.toDouble()
                                ?: .0
                            ) / (
                            (
                                caloriesSubWidget.consumed
                                    ?: 0
                                ) + (caloriesSubWidget.left ?: 0)
                            )
                        ).toFloat(),
                    color = MaterialTheme.colorScheme.primary
                )
                Box(
                    modifier = Modifier
                        .padding(all = Padding.Padding9)
                        .background(
                            brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient),
                            shape = CircleShape
                        )
                        .aspectRatio(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        stringResource(
                            MR.strings.private_area_dashboard_calories_left_value,
                            caloriesSubWidget.left ?: 0
                        ),
                        modifier = Modifier.padding(all = Padding.Padding6),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun WaterIntakeBlock(
    modifier: Modifier,
    waterIntakeSubWidget: HomeScreenData.WaterIntakeSubWidget
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .shadow(elevation = Dimen.Dimen40)
            .clip(RoundedCornerShape(size = Dimen.Dimen20))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(end = Padding.Padding8)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = Padding.Padding20,
                    top = Padding.Padding20,
                    bottom = Padding.Padding20
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(Dimen.Dimen20)
                    .clip(RoundedCornerShape(size = Dimen.Dimen30))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5F)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Box(
                    modifier = Modifier
                        .weight(0.5F)
                        .background(brush = Brush.verticalGradient(MaterialTheme.colorScheme.brandGradient))
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = Padding.Padding10)
            ) {
                Text(
                    stringResource(MR.strings.private_area_dashboard_water_intake_title),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    stringResource(
                        MR.strings.private_area_dashboard_water_intake_liters,
                        waterIntakeSubWidget.amount ?: .0
                    ),
                    modifier = Modifier
                        .textBrush(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient))
                        .padding(top = Padding.Padding5),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    stringResource(MR.strings.private_area_dashboard_water_intake_realtime_updates),
                    modifier = Modifier.padding(top = Padding.Padding10),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                Box(Modifier.height(Dimen.Dimen10))
                waterIntakeSubWidget.intakes?.forEach {
                    Text(
                        it.timeDiapason.orEmpty(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Text(
                        stringResource(
                            MR.strings.private_area_dashboard_water_intake_millis,
                            it.amountInMillis ?: 0
                        ),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .textBrush(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.tertiaryGradient))
                            .padding(top = Padding.Padding3)
                    )
                    Box(Modifier.height(Dimen.Dimen10))
                }
            }
        }
    }
}

@Composable
private fun DrawActivityRing() {
    val circleGradient = MaterialTheme.colorScheme.tertiaryGradient
    Canvas(modifier = Modifier) {
        drawCircle(
            brush = Brush.horizontalGradient(circleGradient),
            radius = Dimen.Dimen4.toPx()
        )
        drawCircle(
            color = Color.White,
            radius = Dimen.Dimen2.toPx()
        )
    }
}

// @Composable
// private fun DrawTooltip(lastHeartRate: Long) {
//    val textToDraw = stringResource(
//        MR.strings.private_area_dashboard_tooltip_minutes_left,
//        lastHeartRate
//    )
//    val paintColor = MaterialTheme.colorScheme.onPrimary
//    val shaderGradient = MaterialTheme.colorScheme.tertiaryGradient
//    Canvas(modifier = Modifier) {
//        val nativeCanvas = this.drawContext.canvas.nativeCanvas
//
//        val textPaint = Paint().apply {
//            textSize = TextSize.Size10.toPx()
//            color = paintColor
//        }
//        val textBounds = Rect()
//
//        textPaint.getTextBounds(textToDraw, 0, textToDraw.length, textBounds)
//
//        val tooltipWidth = textBounds.width().toFloat() + Dimen.Dimen20.toPx()
//        val tooltipHeight = textBounds.height().toFloat() + Dimen.Dimen10.toPx()
//
//        val rect = RectF(
//            0F,
//            0F,
//            tooltipWidth,
//            tooltipHeight
//        )
//        val path = Path()
//        path.addRoundRect(
//            rect,
//            floatArrayOf(50F, 50F, 50F, 50F, 50F, 50F, 50F, 50F),
//            Path.Direction.CW
//        )
//        path.apply {
//            fillType = Path.FillType.EVEN_ODD
//            moveTo(
//                tooltipWidth / 2 - Dimen.Dimen5.toPx(),
//                tooltipHeight
//            )
//            lineTo(
//                tooltipWidth / 2,
//                tooltipHeight + Dimen.Dimen5.toPx()
//            )
//            lineTo(
//                tooltipWidth / 2 + Dimen.Dimen5.toPx(),
//                tooltipHeight
//            )
//            close()
//        }
//
//        nativeCanvas.translate(-(tooltipWidth / 2), 0F)
//
//        nativeCanvas.drawPath(path, Paint().apply {
//            shader = LinearGradientShader(
//                Offset(
//                    0F,
//                    0F
//                ),
//                Offset(
//                    tooltipWidth,
//                    0F
//                ),
//                colors = shaderGradient
//            )
//        })
//
//        nativeCanvas.drawText(
//            textToDraw,
//            Dimen.Dimen10.toPx(),
//            textBounds.height() + Dimen.Dimen5.toPx(),
//            textPaint
//        )
//    }
// }
