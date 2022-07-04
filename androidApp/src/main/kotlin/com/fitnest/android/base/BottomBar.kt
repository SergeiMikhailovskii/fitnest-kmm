package com.fitnest.android.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fitnest.android.enum.BottomNavigationType
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.GrayColor2
import com.fitnest.android.style.Padding
import com.fitnest.android.style.SecondaryColor

@Composable
fun BottomBar(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavigationType.HOME,
        BottomNavigationType.TRACKER,
        BottomNavigationType.PHOTO,
        BottomNavigationType.SETTINGS
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    if (isRouteWithBottomNavigation(currentRoute)) {
        androidx.compose.material.BottomNavigation {
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

private fun isRouteWithBottomNavigation(route: String) = arrayOf(
    Route.PrivateAreaHome.screenName,
    Route.PrivateAreaTracker.screenName,
    Route.PrivateAreaPhoto.screenName,
    Route.PrivateAreaSettings.screenName,
).contains(route)