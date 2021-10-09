package com.mikhailovskii.kmmtest.android.base

sealed class Route(val screenName: String) {
    object Splash : Route("splash")
    data class Onboarding(val progress: Int) : Route("onboarding/${progress}")

    object Login : Route("login")

    // for not implemented routes
    object Unknown : Route("unknown")
}
