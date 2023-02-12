package com.fitnest.android.screen.registration.complete_account.anthropometry

sealed class AnthropometryEvent {
    class Submit(val value: Int) : AnthropometryEvent()
}