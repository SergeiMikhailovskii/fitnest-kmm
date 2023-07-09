package com.fitnest.android.screen.private_area.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.extension.tertiaryGradient
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
internal fun BMIBlock(bmiWidget: HomeScreenData.BMIWidget?, progress: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding30)
            .placeholder(
                progress,
                highlight = PlaceholderHighlight.fade(),
                shape = RoundedCornerShape(Dimen.Dimen16)
            )
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
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )

                bmiWidget?.result?.let {
                    Text(
                        stringResource(id = it),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
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
                            .background(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.tertiaryGradient))
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

            bmiWidget?.index?.let {
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
    val arcGradient = MaterialTheme.colorScheme.tertiaryGradient
    Box(
        modifier = modifier
            .width(300.pxToDp())
            .height(300.pxToDp()),
    ) {
        val circleColor = MaterialTheme.colorScheme.onPrimary
        Canvas(modifier = Modifier) {
            drawCircle(
                color = circleColor,
                radius = 150F,
                center = Offset(150F, 150F)
            )
            drawArc(
                brush = Brush.horizontalGradient(arcGradient),
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
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}
