package com.mikhailovskii.kmmtest.entity

data class OnboardingState(
    val imageResId: Int,
    val title: Int,
    val description: Int,
    val progress: Int
) {
    companion object {
        const val ONBOARDING_SCREENS_AMOUNT = 3
    }
}