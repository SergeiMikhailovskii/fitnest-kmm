package com.fitnest.android.screen.private_area.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.*

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
