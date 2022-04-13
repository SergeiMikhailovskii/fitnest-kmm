package com.fitnest.android.screen.private_area.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    modifier = Modifier.padding(horizontal = Padding.Padding20)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = Padding.Padding26)
                    ) {
                        Text("BMI (Body Mass Index)", style = PoppinsMediumStyle14White)
                        Text("You have a normal weight", style = PoppinsNormalStyle12White)
                        Button(
                            modifier = Modifier
                                .padding(top = Padding.Padding15)
                                .wrapContentSize(),
                            onClick = { },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(brush = Brush.horizontalGradient(SecondaryGradient))
                            ) {
                                Text("View More")
                            }
                        }
                    }
                }
            }
        }
    }
}