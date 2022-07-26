package com.fitnest.android.screen.private_area.home.composable

import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.extension.textBrush
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Composable
fun ActivityStatusBlock(activityStatusWidget: HomeScreenData.ActivityStatusWidget) {
    Column(modifier = Modifier.padding(top = Padding.Padding30)) {
        Text(
            stringResource(id = R.string.private_area_dashboard_activity_status_title),
            style = PoppinsBoldStyle16Black
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

@ExperimentalTime
@Composable
private fun HeartRate(heartRateSubWidget: HomeScreenData.HeartRateSubWidget) {
    var chartWidth by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding15)
            .background(
                brush = Brush.horizontalGradient(BrandGradient),
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
                    stringResource(id = R.string.private_area_dashboard_heart_rate_title),
                    modifier = Modifier.padding(start = Padding.Padding20),
                    style = PoppinsMediumStyle12Black
                )
                Text(
                    stringResource(
                        id = R.string.private_area_dashboard_heart_rate_bpm,
                        heartRateSubWidget.rate ?: 0
                    ),
                    modifier = Modifier
                        .padding(start = Padding.Padding20, top = Padding.Padding5)
                        .textBrush(brush = Brush.horizontalGradient(BrandGradient)),
                    style = PoppinsSemiBoldStyle14
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_private_area_heart_rate_graph),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            chartWidth = it.width
                        },
                    contentScale = ContentScale.FillBounds
                )
            }

            Column {
                Box(
                    modifier = Modifier.padding(
                        start = (chartWidth * 0.62)
                            .toInt()
                            .pxToDp(),
                        top = Padding.Padding50
                    )
                ) {
                    Column {
                        val now = Clock.System.now()
                        val lastHeartRateInstant =
                            heartRateSubWidget.date?.toInstant(TimeZone.currentSystemDefault()) ?: return
                        val diff = now - lastHeartRateInstant
                        val minutesDiff = diff.inWholeMinutes
                        DrawTooltip(minutesDiff)
                    }
                }

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
            }
        }
    }
}

@Composable
private fun SleepBlock(sleepSubWidget: HomeScreenData.SleepSubWidget) {
    Box(
        modifier = Modifier
            .shadow(elevation = Dimen.Dimen40)
            .clip(RoundedCornerShape(size = Dimen.Dimen20))
            .background(Color.White)
            .aspectRatio(1F)
    ) {
        Column {
            Text(
                stringResource(id = R.string.private_area_dashboard_sleep_title),
                modifier = Modifier.padding(
                    start = Padding.Padding20,
                    top = Padding.Padding20
                ),
                style = PoppinsMediumStyle12Black
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
                    .textBrush(brush = Brush.horizontalGradient(BrandGradient)),
                style = PoppinsMediumStyle12
            )
            Image(
                painter = painterResource(id = R.drawable.ic_private_area_sleep_graph),
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
    withStyle(SpanStyle(fontSize = TextSize.Size14)) {
        append(hours.toString())
    }
    withStyle(SpanStyle(fontSize = TextSize.Size10)) {
        append(stringResource(id = R.string.private_area_dashboard_sleep_hours))
    }
    withStyle(SpanStyle(fontSize = TextSize.Size14)) {
        append(" $minutes")
    }
    withStyle(SpanStyle(fontSize = TextSize.Size10)) {
        append(stringResource(id = R.string.private_area_dashboard_sleep_minutes))
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
            .background(Color.White)
            .aspectRatio(1F)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(id = R.string.private_area_dashboard_calories_title),
                modifier = Modifier
                    .padding(
                        start = Padding.Padding20,
                        top = Padding.Padding20
                    )
                    .fillMaxWidth(),
                style = PoppinsMediumStyle12Black
            )
            Text(
                stringResource(
                    id = R.string.private_area_dashboard_calories_value,
                    caloriesSubWidget.consumed ?: 0
                ),
                modifier = Modifier
                    .padding(
                        start = Padding.Padding20,
                        top = Padding.Padding5
                    )
                    .textBrush(brush = Brush.horizontalGradient(BrandGradient))
                    .fillMaxWidth(),
                style = PoppinsMediumStyle14
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
                    progress = ((caloriesSubWidget.consumed?.toDouble()
                        ?: .0) / ((caloriesSubWidget.consumed
                        ?: 0) + (caloriesSubWidget.left ?: 0))).toFloat(),
                    color = BrandColor,
                )
                Box(
                    modifier = Modifier
                        .padding(all = Padding.Padding9)
                        .background(
                            brush = Brush.horizontalGradient(BrandGradient),
                            shape = CircleShape
                        )
                        .aspectRatio(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        stringResource(
                            id = R.string.private_area_dashboard_calories_left_value,
                            caloriesSubWidget.left ?: 0
                        ),
                        modifier = Modifier.padding(all = Padding.Padding6),
                        textAlign = TextAlign.Center,
                        style = PoppinsMediumStyle8White
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
            .background(Color.White)
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
                    .background(BorderColor),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5F)
                        .background(BorderColor)
                )
                Box(
                    modifier = Modifier
                        .weight(0.5F)
                        .background(brush = Brush.verticalGradient(BrandGradient))
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = Padding.Padding10)
            ) {
                Text(
                    stringResource(id = R.string.private_area_dashboard_water_intake_title),
                    style = PoppinsMediumStyle12Black
                )
                Text(
                    stringResource(
                        id = R.string.private_area_dashboard_water_intake_liters,
                        waterIntakeSubWidget.amount ?: .0
                    ),
                    modifier = Modifier
                        .textBrush(brush = Brush.horizontalGradient(BrandGradient))
                        .padding(top = Padding.Padding5),
                    style = PoppinsMediumStyle14
                )
                Text(
                    stringResource(id = R.string.private_area_dashboard_water_intake_realtime_updates),
                    modifier = Modifier.padding(top = Padding.Padding10),
                    style = PoppinsMediumStyle10Gray1
                )
                Box(Modifier.height(Dimen.Dimen10))
                waterIntakeSubWidget.intakes?.forEach {
                    Text(it.timeDiapason.orEmpty(), style = PoppinsNormalStyle8Gray2)
                    Text(
                        stringResource(
                            id = R.string.private_area_dashboard_water_intake_millis,
                            it.amountInMillis ?: 0
                        ),
                        style = PoppinsMediumStyle8,
                        modifier = Modifier
                            .textBrush(brush = Brush.horizontalGradient(SecondaryGradient))
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
    Canvas(modifier = Modifier) {
        drawCircle(
            brush = Brush.horizontalGradient(SecondaryGradient),
            radius = Dimen.Dimen4.toPx(),
        )
        drawCircle(
            color = Color.White,
            radius = Dimen.Dimen2.toPx(),
        )
    }
}

@Composable
private fun DrawTooltip(lastHeartRate: Long) {
    val textToDraw = stringResource(
        id = R.string.private_area_dashboard_tooltip_minutes_left,
        lastHeartRate
    )
    Canvas(modifier = Modifier) {
        val nativeCanvas = this.drawContext.canvas.nativeCanvas

        val textPaint = Paint().apply {
            textSize = TextSize.Size10.toPx()
            color = android.graphics.Color.WHITE
        }
        val textBounds = Rect()

        textPaint.getTextBounds(textToDraw, 0, textToDraw.length, textBounds)

        val tooltipWidth = textBounds.width().toFloat() + Dimen.Dimen20.toPx()
        val tooltipHeight = textBounds.height().toFloat() + Dimen.Dimen10.toPx()

        val rect = RectF(
            0F,
            0F,
            tooltipWidth,
            tooltipHeight
        )
        val path = Path()
        path.addRoundRect(
            rect,
            floatArrayOf(50F, 50F, 50F, 50F, 50F, 50F, 50F, 50F),
            Path.Direction.CW
        )
        path.apply {
            fillType = Path.FillType.EVEN_ODD
            moveTo(
                tooltipWidth / 2 - Dimen.Dimen5.toPx(),
                tooltipHeight
            )
            lineTo(
                tooltipWidth / 2,
                tooltipHeight + Dimen.Dimen5.toPx()
            )
            lineTo(
                tooltipWidth / 2 + Dimen.Dimen5.toPx(),
                tooltipHeight
            )
            close()
        }

        nativeCanvas.translate(-(tooltipWidth / 2), 0F)

        nativeCanvas.drawPath(path, Paint().apply {
            shader = LinearGradientShader(
                Offset(
                    0F,
                    0F
                ),
                Offset(
                    tooltipWidth,
                    0F
                ),
                colors = SecondaryGradient
            )
        })

        nativeCanvas.drawText(
            textToDraw,
            Dimen.Dimen10.toPx(),
            textBounds.height() + Dimen.Dimen5.toPx(),
            textPaint
        )
    }
}
