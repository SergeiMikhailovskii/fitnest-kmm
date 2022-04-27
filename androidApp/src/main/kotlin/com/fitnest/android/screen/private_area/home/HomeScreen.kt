package com.fitnest.android.screen.private_area.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
import com.fitnest.android.extension.pxToDp
import com.fitnest.android.style.*

@Composable
fun HomeScreen() {
    Scaffold {
        Column(
            modifier = Modifier.padding(
                top = Padding.Padding20,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        ) {
            HeaderBlock()
            BMIBlock()
            TodayTargetBlock()
        }
    }
}

@Composable
fun HeaderBlock() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = "Welcome Back,", style = PoppinsNormalStyle12Gray2)
            Text(
                text = "Stefani Wong",
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
fun BMIBlock() {
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
                Text("BMI (Body Mass Index)", style = PoppinsMediumStyle14White)
                Text("You have a normal weight", style = PoppinsNormalStyle12White)
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
                            "View More",
                            modifier = Modifier.padding(
                                horizontal = Padding.Padding20,
                                vertical = Padding.Padding10
                            )
                        )
                    }
                }
            }
            PieChart(
                modifier = Modifier.padding(
                    top = Padding.Padding26,
                    bottom = Padding.Padding26,
                    start = Padding.Padding7
                ),
                progress = 70.0
            )
        }
    }
}

@Composable
fun TodayTargetBlock() {
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
            Text("Today Target", style = PoppinsMediumStyle14Black)
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
                        "Check",
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
            text = "${progress.toInt()} %",
            modifier = Modifier.align(Alignment.Center),
            fontSize = TextSize.Size18,
            color = BrandColor
        )
    }
}