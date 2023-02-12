package com.fitnest.android.screen.registration.complete_account.anthropometry

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
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
            handleRoute(Route.DismissBottomSheet)
        }
    }

    fun dismiss() {
        handleRoute(Route.DismissBottomSheet)
    }
}