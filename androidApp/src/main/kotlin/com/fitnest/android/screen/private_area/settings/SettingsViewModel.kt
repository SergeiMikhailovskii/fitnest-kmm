package com.fitnest.android.screen.private_area.settings

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.domain.usecase.privateArea.GetProfilePageUseCase
import com.fitnest.domain.usecase.privateArea.SetNotificationsEnabledUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getProfilePageUseCase: GetProfilePageUseCase,
    private val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase,
    private val viewMapper: SettingsViewMapper
) : BaseViewModel() {

    private var screenData = SettingsScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    fun getProfilePage() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val profileInfoWidget = getProfilePageUseCase().getOrThrow()
            screenData = viewMapper.mapProfileModelIntoScreenData(profileInfoWidget)
            updateScreenData()
            handleProgress()
        }
    }

    fun setNotificationsEnabled(areNotificationsEnabled: Boolean) {
        viewModelScope.launch {
            setNotificationsEnabledUseCase(areNotificationsEnabled)
            screenData = screenData.copy(areNotificationsEnabled = areNotificationsEnabled)
            updateScreenData()
        }
    }

    fun onActivityHistoryClicked() {
        handleRoute(Route.PrivateArea.ActivityTracker)
    }

    private fun updateScreenData() {
        viewModelScope.launch(exceptionHandler) {
            _screenDataFlow.emit(screenData.copy())
        }
    }
}
