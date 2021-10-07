package com.mikhailovskii.kmmtest.android.base

sealed class Route(val screenName: String) {
    object Splash : Route("splash")
    data class Onboarding(val type: String) : Route("onboarding/${type}")

    object Login : Route("login")
}
