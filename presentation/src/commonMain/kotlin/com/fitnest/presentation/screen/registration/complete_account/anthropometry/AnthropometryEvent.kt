package com.fitnest.presentation.screen.registration.complete_account.anthropometry

sealed class AnthropometryEvent {
    class Submit(val value: Int) : AnthropometryEvent()
}