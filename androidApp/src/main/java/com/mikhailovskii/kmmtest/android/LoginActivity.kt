package com.mikhailovskii.kmmtest.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mikhailovskii.kmmtest.state.LoginResultState


class LoginActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginWindow()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun LoginWindow() {
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        loginViewModel.loginResultLiveData.observe(this, {
            val toastText = when (it) {
                LoginResultState.LoginSuccess -> {
                    "Login success"
                }
                else -> {
                    "Login failed"
                }
            }
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        })

        Scaffold(
            topBar = { TopAppBar(
                title = { Text("KMM Android") }
            )},
            content = {
                Column {
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text("Login") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image =
                                if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(imageVector = image, "")
                            }
                        }
                    )
                }
            },
            bottomBar = {
                Button(
                    onClick = {
                        loginViewModel.loginUser(login, password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Login", modifier = Modifier.padding(8.dp))
                }
            }
        )
    }
}
