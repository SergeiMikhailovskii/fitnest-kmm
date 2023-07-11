package com.fitnest.android.navigation

import androidx.navigation.NavController

internal fun handleNavigation(route: com.fitnest.presentation.navigation.Route, navController: NavController) {
    if (
        arrayOf(
            com.fitnest.presentation.navigation.Route.PrivateArea.Notifications,
            com.fitnest.presentation.navigation.Route.PrivateArea.ActivityTracker
        ).contains(route)
    ) {
        navController.navigate(route.screenName)
    } else {
        navController.popBackStack()
        navController.navigate(route.screenName)
    }
}