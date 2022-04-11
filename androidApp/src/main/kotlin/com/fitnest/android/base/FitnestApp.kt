package com.fitnest.android.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.fitnest.android.enum.BottomNavigationType
import com.fitnest.android.screen.login.LoginScreen
import com.fitnest.android.screen.onboarding.OnboardingScreen
import com.fitnest.android.screen.private_area.home.HomeScreen
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
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

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

    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    FitnestTheme {
        Scaffold(
            bottomBar = {
                if (bottomBarState.value) {
                    BottomNavigation(backgroundColor = Color.Cyan) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        bottomNavigationItems.forEach {
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = it.icon),
                                        contentDescription = stringResource(id = it.title)
                                    )
                                },
                                selected = currentRoute == it.route.screenName,
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
                    bottomBarState.value = false
                }
                composable(route = Route.Login.screenName) {
                    LoginScreen()
                    bottomBarState.value = false
                }
                composable(route = Route.Proxy.screenName) {
                    ProxyScreen(navController = navController)
                    bottomBarState.value = false
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
                    bottomBarState.value = false
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
                    bottomBarState.value = false
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
                    bottomBarState.value = true
                    HomeScreen()
                }
                composable(route = Route.PrivateAreaSettings.screenName) {
                    bottomBarState.value = true
                    SettingsScreen()
                }
                composable(route = Route.PrivateAreaPhoto.screenName) {
                    bottomBarState.value = true
                    PhotoScreen()
                }
                composable(route = Route.PrivateAreaTracker.screenName) {
                    bottomBarState.value = true
                    TrackerScreen()
                }
            }
        }
    }
}