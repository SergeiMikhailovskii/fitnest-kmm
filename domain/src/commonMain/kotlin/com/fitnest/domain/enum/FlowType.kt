package com.fitnest.domain.enum

enum class FlowType {
    ONBOARDING, UNKNOWN;

    companion object {
        fun fromName(name: String?) = when (name) {
            "/onboarding" -> ONBOARDING
            else -> UNKNOWN
        }
    }
}