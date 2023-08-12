package com.fitnest.android.screen.registration.complete_account.anthropometry

import android.widget.NumberPicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.RegistrationModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryViewModel
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
    navigate: (Route) -> Unit = {}
) = subDI(diBuilder = {
    import(RegistrationModule.anthropometryBottomSheetModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel<AnthropometryViewModel>(factory = viewModelFactory)
    val context = LocalContext.current

    com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryBottomSheet(
        viewModel = viewModel,
        picker = {
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
        },
        initialValue = initialValue,
        navigate = navigate
    )
}