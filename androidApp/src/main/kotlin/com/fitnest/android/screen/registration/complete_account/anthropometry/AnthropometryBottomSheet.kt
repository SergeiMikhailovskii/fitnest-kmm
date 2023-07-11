package com.fitnest.android.screen.registration.complete_account.anthropometry

import android.widget.NumberPicker
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.R
import com.fitnest.android.di.RegistrationModule
import com.fitnest.presentation.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Preview
@Composable
private fun AnthropometryBottomSheetPreview() {
    AnthropometryBottomSheet(
        minValue = 0,
        maxValue = 100,
        initialValue = 50
    ) {}
}

@Composable
internal fun AnthropometryBottomSheet(
    minValue: Int,
    maxValue: Int,
    initialValue: Int = 0,
    navigate: (com.fitnest.presentation.navigation.Route) -> Unit = {}
) = subDI(diBuilder = {
    import(RegistrationModule.anthropometryBottomSheetModule)
}) {
    val context = LocalContext.current
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel<AnthropometryViewModel>(factory = viewModelFactory)

    LaunchedEffect(key1 = null) {
        viewModel.setAnthropometryValue(initialValue)
        launch {
            viewModel.routeSharedFlow.collect {
                navigate(it)
            }
        }
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
                context.getString(R.string.registration_complete_account_cancel),
                modifier = Modifier.clickable(onClick = viewModel::dismiss)
            )
            Box(modifier = Modifier.weight(1F))
            Text(
                context.getString(R.string.registration_complete_account_save),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = viewModel::submitValue)
            )
        }
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                NumberPicker(context).apply {
                    this.minValue = minValue
                    this.maxValue = maxValue
                    this.value = initialValue
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                    setOnValueChangedListener { _, _, newVal ->
                        viewModel.setAnthropometryValue(newVal)
                    }
                }
            }
        )
    }
}