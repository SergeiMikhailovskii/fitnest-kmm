package com.fitnest.android.screen.registration.create_account

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.RegistrationModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.create_account.CreateAccountRegistrationViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@ExperimentalMaterial3Api
@Composable
@Preview
internal fun CreateAccountRegistrationScreenPreview() {
    CreateAccountRegistrationScreen {}
}

@ExperimentalMaterial3Api
@Composable
internal fun CreateAccountRegistrationScreen(navigate: (Route) -> Unit) = subDI(diBuilder = {
    import(RegistrationModule.createAccountRegistrationScreenModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = CreateAccountRegistrationViewModel::class.java
    )
    com.fitnest.presentation.screen.registration.create_account.CreateAccountRegistrationScreen(
        viewModel = viewModel,
        onNavigate = navigate
    )
}
