package com.fitnest.android.view.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.presentation.R

@Preview
@Composable
internal fun ForgetPasswordSuccessDialogPreview() {
    ForgetPasswordSuccessDialog {}
}

@Composable
internal fun ForgetPasswordSuccessDialog(onClose: () -> Unit) {
    AlertDialog(onDismissRequest = {
        onClose()
    }, confirmButton = {
        Text(
            text = stringResource(id = R.string.forget_password_success_dialog_confirm),
            modifier = Modifier.clickable {
                onClose()
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
    }, text = {
        Text(
            text = stringResource(id = R.string.forget_password_success_dialog_text),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    })
}