package com.fitnest.android.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fitnest.android.screen.login.LoginScreen
import com.fitnest.android.screen.onboarding.OnboardingScreen
import com.fitnest.android.screen.private_area.home.HomeScreen
import com.fitnest.android.screen.private_area.notification.NotificationsScreen
import com.fitnest.android.screen.private_area.photo.PhotoScreen
import com.fitnest.android.screen.private_area.settings.SettingsScreen
import com.fitnest.android.screen.private_area.tracker.TrackerScreen
import com.fitnest.android.screen.proxy.ProxyScreen
import com.fitnest.android.screen.registration.complete_account.CompleteAccountRegistrationScreen
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationScreen
import com.fitnest.android.screen.registration.goal.GoalRegistrationScreen
import com.fitnest.android.screen.registration.welcome_back.WelcomeBackRegistrationScreen
import com.fitnest.android.screen.splash.SplashScreen
import com.fitnest.android.style.FitnestTheme
import com.fitnest.domain.enum.FlowType
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun FitnestApp() {
    val navController = rememberAnimatedNavController(AnimatedComposeNavigator())

    FitnestTheme {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            topBar = { TopBar(navController) }
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = Route.Splash.screenName
            ) {
                composable(route = Route.Splash.screenName) {
                    SplashScreen(navController = navController)
                }
                composable(route = Route.Login.screenName) {
                    LoginScreen(navController = navController)
                }
                composable(route = "proxy/{flowType}", arguments = listOf(navArgument("flowType") {
                    type = NavType.EnumType(type = FlowType::class.java)
                })) {
                    val flowType = it.arguments?.getSerializable("flowType") as FlowType
                    ProxyScreen(navController = navController, flowType)
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
                        stepName = it.arguments?.getString("stepName").orEmpty()
                    )
                }
                composable(
                    route = "registrationStep/{stepName}",
                    arguments = listOf(
                        navArgument("stepName") { type = NavType.StringType },
                    ),
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
                    when (it.arguments?.getString("stepName").orEmpty()) {
                        "STEP_CREATE_ACCOUNT" -> {
                            CreateAccountRegistrationScreen(navController = navController)
                        }
                        "STEP_COMPLETE_ACCOUNT" -> {
                            CompleteAccountRegistrationScreen(navController = navController)
                        }
                        "STEP_GOAL" -> {
                            GoalRegistrationScreen(navController = navController)
                        }
                        "STEP_WELCOME_BACK" -> {
                            WelcomeBackRegistrationScreen(navController = navController)
                        }
                    }
                }
                composable(route = Route.PrivateAreaHome.screenName) {
                    HomeScreen(navController = navController)
                }
                composable(route = Route.PrivateAreaSettings.screenName) {
                    SettingsScreen()
                }
                composable(route = Route.PrivateAreaPhoto.screenName) {
                    PhotoScreen()
                }
                composable(route = Route.PrivateAreaTracker.screenName) {
                    TrackerScreen()
                }
                composable(route = Route.PrivateAreaNotifications.screenName) {
                    NotificationsScreen()
                }
            }
        }
    }
}