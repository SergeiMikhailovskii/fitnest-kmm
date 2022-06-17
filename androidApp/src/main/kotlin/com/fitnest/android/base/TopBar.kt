package com.fitnest.android.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fitnest.android.R

@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    if (isRouteWithTopBar(currentRoute)) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_top_bar_back),
                contentDescription = null
            )
            Text("Notification", modifier = Modifier.weight(1F), textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = R.drawable.ic_top_bar_options),
                contentDescription = null
            )
        }
    }

}

private fun isRouteWithTopBar(route: String) = arrayOf(
    Route.PrivateAreaNotifications.screenName,
).contains(route)