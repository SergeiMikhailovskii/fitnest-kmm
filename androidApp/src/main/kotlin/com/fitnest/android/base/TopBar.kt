package com.fitnest.android.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.fitnest.android.enum.TopBarType
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Composable
fun TopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

    when (getTopBarType(currentRoute)) {
        TopBarType.WITH_BACK_TITLE_AND_OPTIONS -> TopBarWithBackTitleAndOptions(
            navController = navController,
            currentRoute = currentRoute
        )
        TopBarType.ONLY_TITLE -> TopBarWithTitle(
            currentRoute = currentRoute
        )
        TopBarType.NONE -> Box {}
    }
}

@Composable
private fun TopBarWithBackTitleAndOptions(navController: NavController, currentRoute: String) {
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

@Composable
private fun TopBarWithTitle(currentRoute: String) {
    Row(
        Modifier
            .height(Dimen.Dimen48)
            .padding(horizontal = Padding.Padding16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            topBarScreenName(currentRoute),
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

private fun getTopBarType(route: String) = when (route) {
    Route.PrivateArea.Notifications.screenName,
    Route.PrivateArea.ActivityTracker.screenName -> TopBarType.WITH_BACK_TITLE_AND_OPTIONS
    Route.PrivateArea.Settings.screenName -> TopBarType.ONLY_TITLE
    else -> TopBarType.NONE
}

@Composable
private fun topBarScreenName(route: String) = when (route) {
    Route.PrivateArea.Notifications.screenName -> stringResource(id = R.string.private_area_notifications_screen_title)
    Route.PrivateArea.ActivityTracker.screenName -> stringResource(id = R.string.private_area_activity_tracker_screen_title)
    Route.PrivateArea.Settings.screenName -> stringResource(id = R.string.private_area_profile_screen_title)
    else -> ""
}