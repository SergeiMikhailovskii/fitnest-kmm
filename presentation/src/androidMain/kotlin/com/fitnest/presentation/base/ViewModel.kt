package com.fitnest.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidViewModelScope

actual open class ViewModel : ViewModel() {
    actual val viewModelScope: CoroutineScope
        get() = androidViewModelScope
}
