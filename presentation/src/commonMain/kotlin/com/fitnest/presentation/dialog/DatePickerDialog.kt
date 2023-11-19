package com.fitnest.presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fitnest.presentation.MR
import com.fitnest.presentation.decompose.dialog.date.DateDialogComponent
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(component: DateDialogComponent) {
    val pickerState = rememberDatePickerState(yearRange = 1900..2023)

    androidx.compose.material3.DatePickerDialog(
        onDismissRequest = component::onDismiss,
        confirmButton = {
            Text(
                stringResource(MR.strings.registration_complete_account_save),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(end = Padding.Padding16, bottom = Padding.Padding16)
                    .clickable {
                        pickerState.selectedDateMillis?.let {
                            component.setDateMillis(it)
                        }
                    }
            )
        }
    ) {
        DatePicker(pickerState)
    }
}
