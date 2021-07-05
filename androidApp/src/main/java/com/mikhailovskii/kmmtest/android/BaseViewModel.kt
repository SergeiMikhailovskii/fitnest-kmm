package com.mikhailovskii.kmmtest.android

import androidx.lifecycle.ViewModel
import com.mikhailovskii.kmmtest.Failure

abstract class BaseViewModel : ViewModel() {

    protected fun handleFailure(failure: Failure) {

    }

}