package com.fitnest.domain.enum

enum class FlowType {
    ONBOARDING,
    REGISTRATION,
    MAIN,
    UNKNOWN;

    companion object {
        fun fromName(name: String?) = when {
            name?.contains("onboarding", true) == true -> ONBOARDING
            name?.contains("registration", true) == true -> REGISTRATION
            name?.contains("main", true) == true -> MAIN
            else -> UNKNOWN
        }
    }
}