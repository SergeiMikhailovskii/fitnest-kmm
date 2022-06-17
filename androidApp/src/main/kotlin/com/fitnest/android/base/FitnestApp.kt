package com.fitnest.android.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.fitnest.android.enum.BottomNavigationType
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
import com.fitnest.android.style.*
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
    val bottomNavigationItems = listOf(
        BottomNavigationType.HOME,
        BottomNavigationType.TRACKER,
        BottomNavigationType.PHOTO,
        BottomNavigationType.SETTINGS
    )

    FitnestTheme {
        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: ""
                if (isRouteWithBottomNavigation(currentRoute)) {
                    BottomNavigation {
                        bottomNavigationItems.forEach {
                            val isSelected = currentRoute == it.route.screenName

                            BottomNavigationItem(
                                modifier = Modifier.background(Color.White),
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            painter = painterResource(id = it.icon),
                                            contentDescription = stringResource(id = it.title)
                                        )
                                        if (isSelected) {
                                            Box(
                                                modifier = Modifier
                                                    .padding(top = Padding.Padding3)
                                                    .width(Dimen.Dimen4)
                                                    .height(Dimen.Dimen4)
                                                    .clip(RoundedCornerShape(Dimen.Dimen2))
                                                    .background(SecondaryColor)
                                            )
                                        }
                                    }
                                },
                                selected = isSelected,
                                selectedContentColor = SecondaryColor,
                                unselectedContentColor = GrayColor2,
                                onClick = {
                                    navController.navigate(it.route.screenName) {
                                        navController.graph.startDestinationRoute?.let {
                                            popUpTo(it) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                },
                            )
                        }
                    }
                }
            }
        ) {
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
                composable(route = Route.Proxy.screenName) {
                    ProxyScreen(navController = navController)
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
                    when (it.arguments?.getString("stepName") ?: "") {
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

private fun isRouteWithBottomNavigation(route: String) = arrayOf(
    Route.PrivateAreaHome.screenName,
    Route.PrivateAreaTracker.screenName,
    Route.PrivateAreaPhoto.screenName,
    Route.PrivateAreaSettings.screenName,
).contains(route)