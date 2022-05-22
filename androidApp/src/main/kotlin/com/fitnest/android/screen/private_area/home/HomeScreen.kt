package com.fitnest.android.screen.private_area.home

import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.extension.textBrush
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.*
import org.kodein.di.compose.rememberInstance

@Preview
@Composable
fun HomeScreen() {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = HomeViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(horizontal = Padding.Padding30)
                .padding(bottom = Padding.Padding30)
                .verticalScroll(rememberScrollState())
        ) {
            screenData.headerWidget?.let { HeaderBlock(it) }
            screenData.bmiWidget?.let { BMIBlock(it) }
            screenData.todayTargetWidget?.let { TodayTargetBlock(it) }
            screenData.activityStatusWidget?.let { ActivityStatusBlock(it) }
            screenData.latestWorkoutWidget?.let { LatestWorkoutBlock(it) }
            Box(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
fun HeaderBlock(headerWidget: HomeScreenData.HeaderWidget) {
    Row(
        modifier = Modifier.padding(
            top = Padding.Padding20,
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.private_area_dashboard_header_welcome_back),
                style = PoppinsNormalStyle12Gray2
            )
            Text(
                text = headerWidget.name ?: "",
                modifier = Modifier.padding(top = Padding.Padding5),
                style = PoppinsBoldStyle20Black
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Box(
            modifier = Modifier
                .width(Dimen.Dimen40)
                .height(Dimen.Dimen40)
                .clip(RoundedCornerShape(Dimen.Dimen8))
                .background(BorderColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_private_area_notification),
                contentDescription = null
            )
        }
    }
}

@Composable
fun BMIBlock(bmiWidget: HomeScreenData.BMIWidget) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding30)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_bmi_background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.padding(horizontal = Padding.Padding20),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.padding(vertical = Padding.Padding26)
            ) {
                Text(
                    stringResource(id = R.string.private_area_dashboard_bmi_title),
                    style = PoppinsMediumStyle14White
                )

                bmiWidget.result?.let {
                    Text(
                        stringResource(id = it),
                        style = PoppinsNormalStyle12White
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(top = Padding.Padding15)
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    onClick = { },
                    contentPadding = PaddingValues(),
                    shape = CircleShape,
                ) {
                    Box(
                        modifier = Modifier
                            .background(brush = Brush.horizontalGradient(SecondaryGradient))
                    ) {
                        Text(
                            stringResource(id = R.string.private_area_dashboard_bmi_view_more),
                            modifier = Modifier.padding(
                                horizontal = Padding.Padding20,
                                vertical = Padding.Padding10
                            )
                        )
                    }
                }
            }

            bmiWidget.index?.let {
                PieChart(
                    modifier = Modifier.padding(
                        top = Padding.Padding26,
                        bottom = Padding.Padding26,
                        start = Padding.Padding7
                    ),
                    progress = it
                )
            }
        }
    }
}

@Composable
fun TodayTargetBlock(todayTargetWidget: HomeScreenData.TodayTargetWidget) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding30)
            .background(
                brush = Brush.horizontalGradient(BrandGradient),
                shape = RoundedCornerShape(Dimen.Dimen16),
                alpha = 0.2F
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = Padding.Padding20,
                vertical = Padding.Padding15
            )
        ) {
            Text(
                stringResource(id = R.string.private_area_dashboard_today_target_title),
                style = PoppinsMediumStyle14Black
            )
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = {},
                shape = CircleShape,
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
            ) {
                Box(
                    modifier = Modifier.background(brush = Brush.horizontalGradient(BrandGradient))
                ) {
                    Text(
                        stringResource(id = R.string.private_area_dashboard_today_target_check),
                        modifier = Modifier.padding(
                            horizontal = Padding.Padding20,
                            vertical = Padding.Padding10
                        )
                    )
                }
            }
        }
    }
}

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

@Composable
fun PieChart(modifier: Modifier, progress: Double) {
    Box(
        modifier = modifier
            .width(300.pxToDp())
            .height(300.pxToDp()),
    ) {
        Canvas(modifier = Modifier) {
            drawCircle(
                color = Color.White,
                radius = 150F,
                center = Offset(150F, 150F)
            )
            drawArc(
                brush = Brush.horizontalGradient(SecondaryGradient),
                startAngle = -90F,
                sweepAngle = (360 * progress / 100).toFloat(),
                useCenter = false,
                size = Size(300F, 300F),
                style = Stroke(width = 20F, cap = StrokeCap.Round)
            )
        }
        Text(
            text = stringResource(
                id = R.string.private_area_dashboard_bmi_progress_percent,
                progress.toInt()
            ),
            modifier = Modifier.align(Alignment.Center),
            fontSize = TextSize.Size18,
            color = BrandColor
        )
    }
}

@Composable
fun DrawTooltip(lastHeartRate: Int) {
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

@Composable
fun DrawActivityRing() {
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
fun HeartRate(heartRateSubWidget: HomeScreenData.HeartRateSubWidget) {
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
                        // todo - add parsing of the date
                        DrawTooltip(3)
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
fun SleepBlock(sleepSubWidget: HomeScreenData.SleepSubWidget) {
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
fun CaloriesBlock(caloriesSubWidget: HomeScreenData.CaloriesSubWidget) {
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
                    progress = ((caloriesSubWidget.consumed ?: 0) / ((caloriesSubWidget.consumed
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
fun WaterIntakeBlock(
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
                        waterIntakeSubWidget.amount ?: 0
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
                    Text(it.timeDiapason ?: "", style = PoppinsNormalStyle8Gray2)
                    Text(
                        it.amountInMillis.toString(),
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
fun LatestWorkoutBlock(latestWorkoutWidget: HomeScreenData.LatestWorkoutWidget) {
    Column(modifier = Modifier.padding(top = Padding.Padding30)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(id = R.string.private_area_dashboard_latest_workout_title),
                style = PoppinsSemiBoldStyle16Black
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                stringResource(id = R.string.private_area_dashboard_latest_workout_see_more),
                style = PoppinsMediumStyle12Gray2
            )
        }
        latestWorkoutWidget.workouts?.forEach {
            LatestWorkoutItem(
                modifier = Modifier
                    .padding(bottom = Padding.Padding15)
                    .shadow(Dimen.Dimen40)
                    .background(
                        color = WhiteColor,
                        shape = RoundedCornerShape(size = Dimen.Dimen16)
                    ),
                model = it
            )
        }
    }
}

@Composable
fun LatestWorkoutItem(modifier: Modifier, model: HomeScreenData.Workout) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_workout),
            contentDescription = null,
            modifier = Modifier
                .padding(start = Padding.Padding15)
                .width(Dimen.Dimen50)
                .height(Dimen.Dimen50)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(
                    top = Padding.Padding10,
                    bottom = Padding.Padding15,
                    start = Padding.Padding10,
                    end = Padding.Padding10
                )
                .weight(1F)
        ) {
            Text(model.name ?: "", style = PoppinsMediumStyle12Black)
            Text(
                stringResource(
                    id = R.string.private_area_dashboard_latest_workout_calories_burn,
                    model.calories ?: 0,
                    model.minutes ?: 0
                ),
                style = PoppinsNormalStyle10Gray2,
                modifier = Modifier.padding(top = Padding.Padding5)
            )
            LinearProgressIndicator(
                progress = 0.5F,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Padding.Padding15),
                color = SecondaryColor,
                backgroundColor = BorderColor
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_workout_btn),
            contentDescription = null,
            modifier = Modifier
                .padding(end = Padding.Padding15)
                .width(Dimen.Dimen24)
                .height(Dimen.Dimen24)
                .clip(CircleShape)
        )
    }
}

@Composable
fun buildSleepDurationAnnotatedString(hours: Int, minutes: Int) = buildAnnotatedString {
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