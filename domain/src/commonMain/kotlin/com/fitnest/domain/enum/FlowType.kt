package com.fitnest.domain.enum

enum class FlowType {
    ONBOARDING, REGISTRATION, UNKNOWN;

    companion object {
        fun fromName(name: String?) = when {
            name?.contains("onboarding") == true -> ONBOARDING
            name?.contains("registration") == true -> REGISTRATION
            else -> UNKNOWN
        }
    }
}