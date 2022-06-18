package com.fitnest.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnest.domain.functional.Failure
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _failure = MutableLiveData<Failure>()
    internal val failure: LiveData<Failure> = _failure

    private val _routeSharedFlow = MutableSharedFlow<Route>()
    internal val routeSharedFlow = _routeSharedFlow.asSharedFlow()

    protected val _progressStateFlow = MutableStateFlow(false)
    internal val progressStateFlow = _progressStateFlow.asStateFlow()

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
        println("Failure $failure")
    }

    protected fun handleRoute(route: Route) {
        viewModelScope.launch {
            _routeSharedFlow.emit(route)
        }
    }

    protected fun handleProgress(progress: Boolean = false) {
        viewModelScope.launch {
            _progressStateFlow.emit(progress)
        }
    }

}