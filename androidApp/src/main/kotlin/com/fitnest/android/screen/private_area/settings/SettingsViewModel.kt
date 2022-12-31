package com.fitnest.android.screen.private_area.settings

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.domain.usecase.private_area.GetProfilePageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getProfilePageUseCase: GetProfilePageUseCase,
    private val viewMapper: SettingsViewMapper
) : BaseViewModel() {

    private var screenData = SettingsScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    fun getProfilePage() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val profileInfoWidget = getProfilePageUseCase().getOrThrow() ?: return@launch
            screenData = viewMapper.mapProfileWidgetIntoScreenData(profileInfoWidget)
            updateScreenData()
            handleProgress()
        }
    }

    fun setNotificationsEnabled(areNotificationsEnabled: Boolean) {
        screenData = screenData.copy(areNotificationsEnabled = areNotificationsEnabled)
        updateScreenData()
    }

    private fun updateScreenData() {
        viewModelScope.launch(exceptionHandler) {
            _screenDataFlow.emit(screenData.copy())
        }
    }
}
