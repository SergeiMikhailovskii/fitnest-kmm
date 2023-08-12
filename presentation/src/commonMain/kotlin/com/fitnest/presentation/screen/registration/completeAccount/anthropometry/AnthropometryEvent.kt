package com.fitnest.presentation.screen.registration.completeAccount.anthropometry

sealed class AnthropometryEvent {
    class Submit(val value: Int) : AnthropometryEvent()
}
