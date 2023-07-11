package com.fitnest.presentation.base

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    val viewModelScope: CoroutineScope
}
