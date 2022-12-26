package com.fitnest.android.screen.private_area.settings

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.usecase.private_area.GetProfilePageUseCase
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getProfilePageUseCase: GetProfilePageUseCase
) : BaseViewModel() {

    fun getProfilePage() {
        viewModelScope.launch(exceptionHandler) {
            getProfilePageUseCase()
        }
    }

}