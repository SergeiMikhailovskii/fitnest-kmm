package com.fitnest.android.base

import com.fitnest.domain.enum.FlowType

sealed class Route(val screenName: String) {

    open val pattern: String
        get() = screenName

    object Splash : Route("splash")
    data class Proxy(val flow: FlowType = FlowType.UNKNOWN) : Route("proxy/${flow}") {
        override val pattern: String
            get() = "proxy/{flowType}"
    }

    class OnboardingStep(stepName: String = "") : Route("onboardingStep/$stepName") {
        override val pattern: String
            get() = "onboardingStep/{stepName}"
    }

    object Login : Route("login")

    sealed class Registration(screenName: String) : Route(screenName) {
        class Step(stepName: String = "") : Registration("registrationStep/$stepName") {
            override val pattern: String
                get() = "registrationStep/{stepName}"
        }

        class AnthropometryBottomSheet(
            minValue: Int = 0,
            maxValue: Int = 0,
            initialValue: Int = 0,
            type: String = ""
        ) : Registration("anthropometry?minValue=$minValue&maxValue=$maxValue&initialValue=$initialValue&type=$type") {
            override val pattern: String
                get() = "anthropometry?minValue={minValue}&maxValue={maxValue}&initialValue={initialValue}&type={type}"
        }
    }

    sealed class PrivateArea(screenName: String) : Route(screenName) {
        object Home : PrivateArea("privateAreaHome")
        object Notifications : PrivateArea("privateAreaNotifications")
        object ActivityTracker : PrivateArea("privateAreaActivityTracker")
        sealed class Tracker(screenName: String) : PrivateArea(screenName) {
            object Screen : Tracker("privateAreaTracker")
            object ActivityInputBottomSheet : Tracker("privateAreaTrackerActivityInput")
        }

        object Photo : PrivateArea("privateAreaPhoto")
        object Settings : PrivateArea("privateAreaSettings")
    }

    object DismissBottomSheet : Route("")

    // for not implemented routes
    object Unknown : Route("unknown")
}
