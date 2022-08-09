package com.fitnest.domain.enum

enum class SexType {
    MALE, FEMALE;

    companion object {
        fun fromString(sex: String) = values().first { it.name == sex }
    }
}
