package com.fitnest.android.base

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fitnest.android.enum.BottomNavigationType

@Composable
fun BottomBar(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavigationType.HOME,
        BottomNavigationType.TRACKER,
        BottomNavigationType.PHOTO,
        BottomNavigationType.SETTINGS
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()
    if (isRouteWithBottomNavigation(currentRoute)) {
        NavigationBar {
            bottomNavigationItems.forEach {
                val isSelected = currentRoute == it.route.screenName

                NavigationBarItem(
                    icon = {
                        val iconId = if (isSelected) it.activeIcon else it.inactiveIcon
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = stringResource(id = it.title),
                        )
                    },
                    selected = isSelected,
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
                    }
                )
            }
        }
    }

}

private fun isRouteWithBottomNavigation(route: String) = arrayOf(
    Route.PrivateArea.Home.screenName,
    Route.PrivateArea.Tracker.screenName,
    Route.PrivateArea.Photo.screenName,
    Route.PrivateArea.Settings.screenName,
).contains(route)