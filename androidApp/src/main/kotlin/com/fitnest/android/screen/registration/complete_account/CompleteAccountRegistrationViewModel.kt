package com.fitnest.android.screen.registration.complete_account

import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.enum.SexType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class CompleteAccountRegistrationViewModel(
    private val screenData: CompleteAccountRegistrationScreenData
) : BaseViewModel() {

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun saveSex(sex: SexType) {
        screenData.sex = sex
        updateScreen()
    }

    internal fun updateSexFocus(isFocused: Boolean) {
        screenData.isSexFocused = isFocused
        updateScreen()
    }

    internal fun saveBirthDate(date: Date) {
        screenData.dateOfBirth = date
        updateScreen()
    }

    internal fun saveWeight(weight: Int) {
        screenData.weight = weight
        updateScreen()
    }

    internal fun saveHeight(height: Int) {
        screenData.height = height
        updateScreen()
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }
}