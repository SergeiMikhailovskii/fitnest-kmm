package com.mikhailovskii.kmmtest.android.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.android.style.BrandGradient
import com.mikhailovskii.kmmtest.android.style.poppinsFamily

@Composable
fun SplashScreen(navController: NavController) {

//    LaunchedEffect(key1 = true) {
//        delay(3000)
//        navController.navigate("login")
//    }

    Scaffold {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(colors = BrandGradient)
                )
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
            OutlinedButton(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, bottom = 40.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}