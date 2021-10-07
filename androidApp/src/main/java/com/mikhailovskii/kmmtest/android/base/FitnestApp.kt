package com.mikhailovskii.kmmtest.android.base

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.mikhailovskii.kmmtest.android.style.FitnestTheme
import com.mikhailovskii.kmmtest.android.view.login.LoginScreen
import com.mikhailovskii.kmmtest.android.view.onboarding.OnboardingScreen
import com.mikhailovskii.kmmtest.android.view.splash.SplashScreen

@Composable
fun FitnestApp() {
    val navController = rememberNavController()

    FitnestTheme {
        Scaffold {
            NavHost(navController = navController, startDestination = Route.Splash.screenName) {
                composable(route = Route.Splash.screenName) {
                    SplashScreen(navController = navController)
                }
                composable(route = Route.Login.screenName) {
                    LoginScreen()
                }
                composable(
                    route = "onboarding/{type}",
                    arguments = listOf(navArgument("type") { type = NavType.StringType })
                ) {
                    OnboardingScreen(navController = navController, type = it.arguments?.getString("type") ?: "")
                }
            }
        }
    }
}