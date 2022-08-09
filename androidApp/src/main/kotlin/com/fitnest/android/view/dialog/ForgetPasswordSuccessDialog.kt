package com.fitnest.android.view.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.style.PoppinsBoldStyle16
import com.fitnest.android.style.PoppinsBoldStyle16Brand
import com.fitnest.android.style.PoppinsNormalStyle16

@Preview
@Composable
internal fun ForgetPasswordSuccessDialog() {
    ForgetPasswordSuccessDialog {}
}

@Composable
internal fun ForgetPasswordSuccessDialog(onClose: () -> Unit) {
    AlertDialog(onDismissRequest = {
        onClose()
    }, confirmButton = {
        Text(
            text = "OK", modifier = Modifier.clickable {
                onClose()
            },
            style = PoppinsBoldStyle16Brand,
        )
    }, text = {
        Text(
            text = "Password was successfully sent to your email. Check it out, please",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = PoppinsNormalStyle16
        )
    })
}