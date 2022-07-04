package com.fitnest.android.base

sealed class Route(val screenName: String) {
    object Splash : Route("splash")
    object Proxy : Route("proxy")

    data class OnboardingStep(val stepName: String) : Route("onboardingStep/$stepName")

    object Login : Route("login")

    data class RegistrationStep(
        val stepName: String
    ) : Route("registrationStep/$stepName")

    abstract class PrivateAreaRoute(screenName: String) : Route(screenName)
    object PrivateAreaHome : PrivateAreaRoute("privateAreaHome")
    object PrivateAreaNotifications : PrivateAreaRoute("privateAreaNotifications")
    object PrivateAreaTracker : PrivateAreaRoute("privateAreaTracker")
    object PrivateAreaPhoto : PrivateAreaRoute("privateAreaPhoto")
    object PrivateAreaSettings : PrivateAreaRoute("privateAreaSettings")

    // for not implemented routes
    object Unknown : Route("unknown")
}
