package com.fitnest.android.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fitnest.android.R
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

    if (isRouteWithTopBar(currentRoute)) {
        Row(
            Modifier
                .height(Dimen.Dimen48)
                .padding(horizontal = Padding.Padding16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_top_bar_back),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = navController::navigateUp)
            )
            Text(
                topBarScreenName(currentRoute),
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_top_bar_options),
                contentDescription = null
            )
        }
    }

}

private fun isRouteWithTopBar(route: String) = arrayOf(
    Route.PrivateAreaNotifications.screenName,
    Route.PrivateAreaActivityTracker.screenName
).contains(route)

@Composable
private fun topBarScreenName(route: String) = when (route) {
    Route.PrivateAreaNotifications.screenName -> stringResource(id = R.string.private_area_notifications_screen_title)
    Route.PrivateAreaActivityTracker.screenName -> stringResource(id = R.string.private_area_activity_tracker_screen_title)
    else -> ""
}