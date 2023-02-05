package com.fitnest.android.navigation

import androidx.navigation.NavController
import com.fitnest.android.base.Route

internal fun handleNavigation(route: Route, navController: NavController) {
    if (
        arrayOf(
            Route.PrivateArea.Notifications,
            Route.PrivateArea.ActivityTracker
        ).contains(route)
    ) {
        navController.navigate(route.screenName)
    } else {
        navController.popBackStack()
        navController.navigate(route.screenName)
    }
}