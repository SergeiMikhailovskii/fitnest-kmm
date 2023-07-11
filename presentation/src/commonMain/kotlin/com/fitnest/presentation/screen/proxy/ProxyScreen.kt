package com.fitnest.presentation.screen.proxy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fitnest.domain.enum.FlowType
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Composable
fun ProxyScreen(flowType: FlowType, viewModel: ProxyViewModel, onNavigate: (Route) -> Unit) {
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    LaunchedEffect(null) {
        launch { viewModel.routeSharedFlow.collect(onNavigate) }
        launch { viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure) }
        viewModel.getNextFlow(flowType)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
