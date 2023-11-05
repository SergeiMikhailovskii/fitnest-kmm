package com.fitnest.presentation.decompose.registration

import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEventsBus
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEventsBusImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val registrationDIModule = DI.Module("registrationDIModule") {
    bindSingleton { RegistrationScreenState() }
    bindSingleton<AnthropometryEventsBus> { AnthropometryEventsBusImpl() }
}
