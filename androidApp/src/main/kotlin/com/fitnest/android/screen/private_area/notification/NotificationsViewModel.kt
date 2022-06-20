package com.fitnest.android.screen.private_area.notification

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import kotlinx.coroutines.launch

internal class NotificationsViewModel(
    private val getNotificationsPageUseCase: GetNotificationsPageUseCase
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            val result = getNotificationsPageUseCase()
            println()
        }
    }

}