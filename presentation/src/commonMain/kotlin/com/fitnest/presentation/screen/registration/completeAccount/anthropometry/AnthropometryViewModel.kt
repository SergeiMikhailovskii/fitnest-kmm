package com.fitnest.presentation.screen.registration.completeAccount.anthropometry

import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class AnthropometryViewModel(
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
