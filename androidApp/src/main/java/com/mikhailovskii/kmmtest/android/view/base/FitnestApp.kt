package com.mikhailovskii.kmmtest.android.view.base

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mikhailovskii.kmmtest.android.view.login.LoginScreen
import com.mikhailovskii.kmmtest.android.view.splash.SplashScreen

@Composable
fun FitnestApp() {
    val navController = rememberNavController()

    FitnestTheme {
        Scaffold {
            NavHost(navController = navController, startDestination = "splash") {
                composable(route = "splash") {
                    SplashScreen(navController)
                }
                composable(route = "login") {
                    LoginScreen()
                }
            }
        }
    }
}