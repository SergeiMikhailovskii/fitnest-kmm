package com.fitnest.domain.entity

data class OnboardingState(
    val imageResId: Int,
    val title: Int,
    val description: Int,
    val progress: Int
) {
    companion object {
        const val FIRST_SCREEN_PROGRESS = 1
        const val SECOND_SCREEN_PROGRESS = 2
        const val THIRD_SCREEN_PROGRESS = 3
        const val FORTH_SCREEN_PROGRESS = 4
        const val ONBOARDING_MAX_PROGRESS = 4
    }
}