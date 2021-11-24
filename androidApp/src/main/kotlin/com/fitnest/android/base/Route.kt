package com.fitnest.android.base

sealed class Route(val screenName: String) {
    object Splash : Route("splash")

    object Onboarding : Route("onboarding")
    data class OnboardingStep(val stepName: String) : Route("onboardingStep/$stepName")

    object Login : Route("login")

    data class RegistrationStep(val stepName: String) : Route("registrationStep/$stepName")

    // for not implemented routes
    object Unknown : Route("unknown")
}
