package com.fitnest.domain.enum

enum class FlowType {
    ONBOARDING, REGISTRATION, UNKNOWN;

    companion object {
        fun fromName(name: String?) = when (name) {
            "/onboarding" -> ONBOARDING
            "/registration" -> REGISTRATION
            else -> UNKNOWN
        }
    }
}