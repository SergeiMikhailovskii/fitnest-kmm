package com.mikhailovskii.kmmtest.android.view.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mikhailovskii.kmmtest.android.view.base.BrandGradient

@Preview
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

        }
    }
}