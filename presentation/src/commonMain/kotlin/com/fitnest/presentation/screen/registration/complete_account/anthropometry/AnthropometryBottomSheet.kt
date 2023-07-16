package com.fitnest.presentation.screen.registration.complete_account.anthropometry

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fitnest.presentation.MR
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch

@Composable
fun AnthropometryBottomSheet(
    viewModel: AnthropometryViewModel,
    picker: @Composable () -> Unit,
    initialValue: Int,
    navigate: (Route) -> Unit
) {
    LaunchedEffect(key1 = null) {
        viewModel.setAnthropometryValue(initialValue)
        launch { viewModel.routeSharedFlow.collect(navigate) }
    }

    Column(modifier = Modifier.background(color = Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Padding.Padding20,
                    end = Padding.Padding20,
                    top = Padding.Padding20
                )
        ) {
            Text(
                stringResource(MR.strings.registration_complete_account_cancel),
                modifier = Modifier.clickable(onClick = viewModel::dismiss)
            )
            Box(modifier = Modifier.weight(1F))
            Text(
                stringResource(MR.strings.registration_complete_account_save),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = viewModel::submitValue)
            )
        }
        picker()
    }
}