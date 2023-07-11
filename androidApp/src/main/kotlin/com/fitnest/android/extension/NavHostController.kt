package com.fitnest.android.extension

import androidx.navigation.NavHostController

internal fun NavHostController.navigate(route: com.fitnest.presentation.navigation.Route) {
    if (route == com.fitnest.presentation.navigation.Route.DismissBottomSheet) {
        popBackStack()
    } else if (arrayOf(
            com.fitnest.presentation.navigation.Route.PrivateArea.Notifications,
            com.fitnest.presentation.navigation.Route.PrivateArea.ActivityTracker,
            com.fitnest.presentation.navigation.Route.PrivateArea.Tracker.ActivityInputBottomSheet
        ).contains(route)
    ) {
        navigate(route.screenName)
    } else {
        popBackStack()
        navigate(route.screenName)
    }
}