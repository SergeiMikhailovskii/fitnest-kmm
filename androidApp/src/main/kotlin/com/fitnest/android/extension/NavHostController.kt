package com.fitnest.android.extension

import androidx.navigation.NavHostController
import com.fitnest.android.base.Route

internal fun NavHostController.navigate(route: Route) {
    if (arrayOf(
            Route.PrivateAreaNotifications,
            Route.PrivateAreaActivityTracker
        ).contains(route)
    ) {
        navigate(route.screenName)
    } else {
        popBackStack()
        navigate(route.screenName)
    }
}