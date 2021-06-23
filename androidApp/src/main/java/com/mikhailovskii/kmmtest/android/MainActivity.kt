package com.mikhailovskii.kmmtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginWindow()
        }
    }

    @Preview
    @Composable
    fun LoginWindow() {
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(Modifier.fillMaxSize()) {
            Spacer(
                Modifier
                    .matchParentSize()
                    .background(Color(1f, 1f, 1f))
            )
            Column(Modifier.width(IntrinsicSize.Max)) {
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Login") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") }
                )
            }
        }
    }
}
