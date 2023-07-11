package com.fitnest.android.screen.proxy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.proxyModule
import com.fitnest.domain.enum.FlowType
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.proxy.ProxyViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Preview
@Composable
internal fun ProxyScreenPreview() {
    ProxyScreen(flowType = FlowType.UNKNOWN) {}
}

@Composable
internal fun ProxyScreen(flowType: FlowType, navigate: (Route) -> Unit) = subDI(
    diBuilder = { import(proxyModule, allowOverride = true) },
    allowSilentOverride = true
) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = ProxyViewModel::class.java
    )

    com.fitnest.presentation.screen.proxy.ProxyScreen(flowType = flowType, viewModel = viewModel, onNavigate = navigate)
}
