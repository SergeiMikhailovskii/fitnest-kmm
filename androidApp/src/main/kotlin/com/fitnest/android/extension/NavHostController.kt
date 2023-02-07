package com.fitnest.android.extension

import androidx.navigation.NavHostController
import com.fitnest.android.base.Route

internal fun NavHostController.navigate(route: Route) {
    if (route == Route.DismissBottomSheet) {
        popBackStack()
    } else if (arrayOf(
            Route.PrivateArea.Notifications,
            Route.PrivateArea.ActivityTracker,
            Route.PrivateArea.Tracker.ActivityInputBottomSheet
        ).contains(route)
    ) {
        navigate(route.screenName)
    } else {
        popBackStack()
        navigate(route.screenName)
    }
}