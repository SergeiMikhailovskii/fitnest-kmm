package com.mikhailovskii.kmmtest.android.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailovskii.kmmtest.Failure

abstract class BaseViewModel : ViewModel() {

    private val _failure = MutableLiveData<Failure>()
    internal val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

}