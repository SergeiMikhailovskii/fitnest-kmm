package com.fitnest.android.base

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fitnest.android.extension.navigate
import com.fitnest.android.internal.SnackbarDelegate
import com.fitnest.android.screen.login.LoginScreen
import com.fitnest.android.screen.onboarding.OnboardingScreen
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerScreen
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputBottomSheet
import com.fitnest.android.screen.private_area.home.HomeScreen
import com.fitnest.android.screen.private_area.notification.NotificationsScreen
import com.fitnest.android.screen.private_area.photo.PhotoScreen
import com.fitnest.android.screen.private_area.settings.SettingsScreen
import com.fitnest.android.screen.private_area.tracker.TrackerScreen
import com.fitnest.android.screen.proxy.ProxyScreen
import com.fitnest.android.screen.registration.complete_account.anthropometry.AnthropometryBottomSheet
import com.fitnest.android.screen.registration.complete_account.screen.CompleteAccountRegistrationScreen
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationScreen
import com.fitnest.android.screen.registration.goal.GoalRegistrationScreen
import com.fitnest.android.screen.registration.welcome_back.WelcomeBackRegistrationScreen
import com.fitnest.android.screen.splash.SplashScreen
import com.fitnest.android.style.FitnestTheme
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.extension.orZero
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import org.kodein.di.compose.rememberInstance

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun FitnestApp(
    startDestination: String = com.fitnest.presentation.navigation.Route.Splash.pattern,
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        SwipeableDefaults.AnimationSpec
    ),
    bottomSheetNavigator: BottomSheetNavigator = remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    },
    navController: NavHostController = rememberAnimatedNavController(bottomSheetNavigator)
) {
    val snackbarDelegate: SnackbarDelegate by rememberInstance()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    snackbarDelegate.apply {
        this.snackbarHostState = snackbarHostState
        coroutineScope = rememberCoroutineScope()
    }

    FitnestTheme {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            topBar = { TopBar(navController) },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) {
                    val backgroundColor = snackbarDelegate.snackbarBackgroundColor
                    Snackbar(
                        snackbarData = it,
                        containerColor = backgroundColor,
                        modifier = Modifier.testTag(snackbarDelegate.snackbarTestTag)
                    )
                }
            }
        ) {
            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            ) {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = startDestination,
                    modifier = Modifier.padding(it)
                ) {
                    composable(route = com.fitnest.presentation.navigation.Route.Splash.pattern) {
                        SplashScreen(navController::navigate)
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.Login.pattern) {
                        LoginScreen(navController = navController)
                    }
                    composable(
                        route = com.fitnest.presentation.navigation.Route.Proxy().pattern,
                        arguments = listOf(navArgument("flowType") {
                            type = NavType.EnumType(type = FlowType::class.java)
                        })
                    ) {
                        val flowType = it.arguments?.getSerializable("flowType") as FlowType
                        ProxyScreen(flowType, navController::navigate)
                    }
                    composable(
                        route = com.fitnest.presentation.navigation.Route.OnboardingStep().pattern,
                        arguments = listOf(navArgument("stepName") { type = NavType.StringType }),
                        enterTransition = {
                            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
                        },
                    ) {
                        OnboardingScreen(
                            it.arguments?.getString("stepName").orEmpty(),
                            navController::navigate
                        )
                    }
                    composable(
                        route = com.fitnest.presentation.navigation.Route.Registration.Step().pattern,
                        arguments = listOf(
                            navArgument("stepName") { type = NavType.StringType },
                        ),
                        enterTransition = {
                            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(300)
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
                        },
                    ) {
                        when (it.arguments?.getString("stepName").orEmpty()) {
                            "STEP_CREATE_ACCOUNT" -> CreateAccountRegistrationScreen(navController::navigate)
                            "STEP_COMPLETE_ACCOUNT" -> CompleteAccountRegistrationScreen(navController::navigate)

                            "STEP_GOAL" -> GoalRegistrationScreen(navController = navController)
                            "STEP_WELCOME_BACK" -> WelcomeBackRegistrationScreen(navController = navController)
                        }
                    }
                    bottomSheet(route = com.fitnest.presentation.navigation.Route.Registration.AnthropometryBottomSheet().pattern) {
                        val minValue = it.arguments?.getString("minValue")?.toIntOrNull().orZero
                        val maxValue = it.arguments?.getString("maxValue")?.toIntOrNull().orZero
                        val initialValue =
                            it.arguments?.getString("initialValue")?.toIntOrNull().orZero
                        AnthropometryBottomSheet(
                            minValue = minValue,
                            maxValue = maxValue,
                            initialValue = initialValue,
                            navigate = navController::navigate
                        )
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.Home.pattern) {
                        HomeScreen(navController = navController)
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.Settings.pattern) {
                        SettingsScreen(navController = navController)
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.Photo.pattern) {
                        PhotoScreen()
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.Tracker.Screen.pattern) {
                        TrackerScreen()
                    }
                    bottomSheet(route = com.fitnest.presentation.navigation.Route.PrivateArea.Tracker.ActivityInputBottomSheet.pattern) {
                        ActivityInputBottomSheet(sheetState, navController::navigate)
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.Notifications.pattern) {
                        NotificationsScreen()
                    }
                    composable(route = com.fitnest.presentation.navigation.Route.PrivateArea.ActivityTracker.pattern) {
                        ActivityTrackerScreen(navController, navController::navigate)
                    }
                }
            }
        }
    }
}