package com.fitnest.domain.entity

data class OnboardingState(
    val imageResId: Int,
    val title: Int,
    val description: Int,
    val progress: Float,
    val previousProgress: Float
) {
    companion object {
        const val ZERO_SCREEN_PROGRESS = 0F
        const val FIRST_SCREEN_PROGRESS = 0.25F
        const val SECOND_SCREEN_PROGRESS = 0.5F
        const val THIRD_SCREEN_PROGRESS = 0.75F
        const val FORTH_SCREEN_PROGRESS = 1F
        const val ONBOARDING_MAX_PROGRESS = 1F
    }
}