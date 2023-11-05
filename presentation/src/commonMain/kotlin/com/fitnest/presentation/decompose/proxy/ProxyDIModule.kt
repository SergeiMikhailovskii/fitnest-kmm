package com.fitnest.presentation.decompose.proxy

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.proxy.GetFlowUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val proxyDIModule by lazy {
    DI.Module("proxyDIModule") {
        bindFactory<ProxyComponentDIParams, ProxyComponent> {
            val context = it.componentContext
            val flowType = it.flowType
            val onNavigate = it.onNavigate

            DefaultProxyComponent(
                context,
                flowType,
                instance(),
                instance(),
                instance(),
                onNavigate
            )
        }
        bindProvider { GetFlowUseCase(instance(), instance()) }
        bindProvider { GetOnboardingStepUseCase(instance(), instance(), instance()) }
        bindProvider { GetRegistrationStepData(instance(), instance(), instance(), instance()) }
    }
}

class ProxyComponentDIParams(
    val componentContext: ComponentContext,
    val flowType: FlowType,
    val onNavigate: (Route) -> Unit
)
