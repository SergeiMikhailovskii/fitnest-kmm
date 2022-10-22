package com.fitnest.android.enum

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fitnest.android.R
import com.fitnest.android.base.Route

enum class BottomNavigationType(
    @StringRes val title: Int,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    val route: Route
) {
    HOME(
        title = R.string.private_area_home_tab_name,
        activeIcon = R.drawable.ic_private_area_home_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_home_unfilled_tab,
        route = Route.PrivateAreaHome
    ),
    TRACKER(
        title = R.string.private_area_tracker_tab_name,
        activeIcon = R.drawable.ic_private_area_progress_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_progress_unfilled_tab,
        route = Route.PrivateAreaTracker
    ),
    PHOTO(
        title = R.string.private_area_photo_tab_name,
        activeIcon = R.drawable.ic_private_area_camera_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_camera_unfilled_tab,
        route = Route.PrivateAreaPhoto
    ),
    SETTINGS(
        title = R.string.private_area_settings_tab_name,
        activeIcon = R.drawable.ic_private_area_profile_filled_tab,
        inactiveIcon = R.drawable.ic_private_area_profile_unfilled_tab,
        route = Route.PrivateAreaSettings
    ),
}