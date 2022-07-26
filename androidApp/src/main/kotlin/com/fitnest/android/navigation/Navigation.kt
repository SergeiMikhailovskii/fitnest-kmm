package com.fitnest.android.navigation

import androidx.navigation.NavController
import com.fitnest.android.base.Route

internal fun handleNavigation(route: Route, navController: NavController) {
    if (route == Route.PrivateAreaNotifications) {
        navController.navigate(route.screenName)
    } else {
        navController.popBackStack()
        navController.navigate(route.screenName)
    }
}