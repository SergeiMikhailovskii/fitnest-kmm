package com.fitnest.android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnest.domain.functional.Failure
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is Failure) {
            handleFailure(throwable)
        }
    }

    private val _failureSharedFlow = MutableSharedFlow<Failure>()
    internal val failureSharedFlow: SharedFlow<Failure> = _failureSharedFlow.asSharedFlow()

    private val _routeSharedFlow = MutableSharedFlow<Route>()
    internal val routeSharedFlow = _routeSharedFlow.asSharedFlow()

    private val _progressSharedFlow = MutableSharedFlow<Boolean>()
    internal val progressSharedFlow = _progressSharedFlow.asSharedFlow()

    protected fun handleRoute(route: Route) {
        viewModelScope.launch(exceptionHandler) {
            _routeSharedFlow.emit(route)
        }
    }

    protected fun handleProgress(progress: Boolean = false) {
        viewModelScope.launch(exceptionHandler) {
            _progressSharedFlow.emit(progress)
        }
    }

    protected fun handleFailure(failure: Failure) {
        viewModelScope.launch(exceptionHandler) {
            _failureSharedFlow.emit(failure)
            _progressSharedFlow.emit(false)
        }
    }
}