package com.fitnest.android.screen.registration.complete_account.anthropometry

import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

internal class AnthropometryViewModel(
    private val anthropometryEventsBus: AnthropometryEventsBusPublisher
) : BaseViewModel() {

    private var screenData = AnthropometryScreenData()

    fun setAnthropometryValue(value: Int) {
        screenData = screenData.copy(value = value)
    }

    fun submitValue() {
        viewModelScope.launch {
            anthropometryEventsBus.sendEvent(AnthropometryEvent.Submit(screenData.value))
            handleRoute(com.fitnest.presentation.navigation.Route.DismissBottomSheet)
        }
    }

    fun dismiss() {
        handleRoute(com.fitnest.presentation.navigation.Route.DismissBottomSheet)
    }
}