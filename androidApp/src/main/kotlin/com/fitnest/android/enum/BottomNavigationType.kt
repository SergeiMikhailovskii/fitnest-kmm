package com.fitnest.android.enum

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fitnest.android.R

enum class BottomNavigationType(
    @StringRes val title: Int,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    val route: com.fitnest.presentation.navigation.Route
) {
    HOME(
        title = R.string.private_area_home_tab_name,
        activeIcon = R.drawable.ic_private_area_home_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_home_unfilled_tab,
        route = com.fitnest.presentation.navigation.Route.PrivateArea.Home
    ),
    TRACKER(
        title = R.string.private_area_tracker_tab_name,
        activeIcon = R.drawable.ic_private_area_progress_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_progress_unfilled_tab,
        route = com.fitnest.presentation.navigation.Route.PrivateArea.Tracker.Screen
    ),
    PHOTO(
        title = R.string.private_area_photo_tab_name,
        activeIcon = R.drawable.ic_private_area_camera_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_camera_unfilled_tab,
        route = com.fitnest.presentation.navigation.Route.PrivateArea.Photo
    ),
    SETTINGS(
        title = R.string.private_area_settings_tab_name,
        activeIcon = R.drawable.ic_private_area_profile_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_profile_unfilled_tab,
        route = com.fitnest.presentation.navigation.Route.PrivateArea.Settings
    ),
}