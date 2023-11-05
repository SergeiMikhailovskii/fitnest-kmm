package com.fitnest.presentation.decompose.splash

import com.arkivanov.decompose.value.Value

interface SplashComponent {
    val model: Value<Model>

    fun onButtonNextClicked()

    data class Model(
        val isLoading: Boolean = false
    )
}
