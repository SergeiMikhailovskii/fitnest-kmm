package com.fitnest.android.base

import com.fitnest.domain.enum.FlowType

sealed class Route(val screenName: String) {
    object Splash : Route("splash")
    data class Proxy(val flow: FlowType = FlowType.UNKNOWN) : Route("proxy/${flow}")

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
