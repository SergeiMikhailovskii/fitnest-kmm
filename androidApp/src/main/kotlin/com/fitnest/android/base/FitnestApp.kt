package com.fitnest.android.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fitnest.android.style.FitnestTheme
import com.fitnest.android.view.login.LoginScreen
import com.fitnest.android.view.onboarding.OnboardingProxyScreen
import com.fitnest.android.view.onboarding.OnboardingScreen
import com.fitnest.android.view.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun FitnestApp() {
    val navController = rememberAnimatedNavController(AnimatedComposeNavigator())

    FitnestTheme {
        Scaffold {
            AnimatedNavHost(
                navController = navController,
                startDestination = Route.Splash.screenName
            ) {
                composable(route = Route.Splash.screenName) {
                    SplashScreen(navController = navController)
                }
                composable(route = Route.Login.screenName) {
                    LoginScreen()
                }
                composable(route = Route.Onboarding.screenName) {
                    OnboardingProxyScreen(navController = navController)
                }
                composable(
                    route = "onboardingStep/{stepName}",
                    arguments = listOf(navArgument("stepName") { type = NavType.StringType }),
                    enterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
                    },
                    exitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
                    },
                    popEnterTransition = { _, _ ->
                        slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
                    },
                    popExitTransition = { _, _ ->
                        slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
                    },
                ) {
                    OnboardingScreen(
                        navController = navController,
                        stepName = it.arguments?.getString("stepName") ?: ""
                    )
                }
            }
        }
    }
}