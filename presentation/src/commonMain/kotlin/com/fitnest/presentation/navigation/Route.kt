package com.fitnest.presentation.navigation

import com.fitnest.domain.enum.FlowType

sealed class Route(val screenName: String) {

    open val pattern: String
        get() = screenName

    object Splash : Route("splash")
    data class Proxy(val flow: FlowType = FlowType.UNKNOWN) : Route("proxy/$flow") {
        override val pattern: String
            get() = "proxy/{flowType}"
    }

    open class Onboarding(val initialStep: String) : Route("onboarding") {
        class Step(stepName: String) : Onboarding(stepName)
    }

    open class Registration2(val initialStep: String) : Route("registration") {
        class Step(val stepName: String) : Registration2("")
    }

    class OnboardingStep(stepName: String = "") : Route("onboardingStep/$stepName") {
        override val pattern: String
            get() = "onboardingStep/{stepName}"
    }

    object Login : Route("login")

    @Deprecated("should be deleted after Android part decompose refactoring. Use Registration2 instead")
    sealed class Registration(screenName: String) : Route(screenName) {
        data class Step(val stepName: String = "") : Registration("registrationStep/$stepName") {
            override val pattern: String
                get() = "registrationStep/{stepName}"
        }

        data class AnthropometryBottomSheet(
            val minValue: Int = 0,
            val maxValue: Int = 0,
            val initialValue: Int = 0,
            val type: String = ""
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
}
