package com.fitnest.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual open class ViewModel {
    actual val viewModelScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.Main)
}
