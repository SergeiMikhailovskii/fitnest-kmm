package com.fitnest.domain.enum

enum class FlowType {
    ONBOARDING, REGISTRATION, UNKNOWN;

    companion object {
        fun fromName(name: String?) = when {
            name?.contains("onboarding", true) == true -> ONBOARDING
            name?.contains("registration", true) == true -> REGISTRATION
            else -> UNKNOWN
        }
    }
}