package com.mikhailovskii.kmmtest.dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//expect val ioDispatcher: CoroutineContext
//
//expect val uiDispatcher: CoroutineContext
//
//expect fun ktorScope(block: suspend () -> Unit)

fun ktorScope(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        block()
    }
}