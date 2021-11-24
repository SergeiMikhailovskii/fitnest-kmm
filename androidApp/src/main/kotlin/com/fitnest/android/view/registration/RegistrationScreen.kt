package com.fitnest.android.view.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController, stepName: String) {
    Scaffold {
        Column {
            Text("Hey there,")
            Text("Create an Account")
            TextField(value = "", onValueChange = { })
            TextField(value = "", onValueChange = { })
            TextField(value = "", onValueChange = { })
            TextField(value = "", onValueChange = { })
            Checkbox(checked = true, onCheckedChange = { })
            Button(onClick = {}) {}
        }
    }
}
