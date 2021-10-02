package com.mikhailovskii.kmmtest.android.view.dialog

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun LoginSuccessDialog() {
    var dialogVisibility by remember { mutableStateOf(false) }

//    loginViewModel.loginResultLiveData.observe(this, {
//        dialogVisibility = it == LoginResultState.LOGIN_SUCCESS
//    })

    if (dialogVisibility) {
        AlertDialog(onDismissRequest = {},
            confirmButton = {
                ClickableText(text = AnnotatedString("Ok")) {
//                    loginViewModel.dismissLoginDialog()
                }
            }, text = {
                Text("Login success")
            })
    }
}