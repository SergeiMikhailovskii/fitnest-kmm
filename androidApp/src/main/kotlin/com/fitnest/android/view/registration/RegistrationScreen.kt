package com.fitnest.android.view.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController, stepName: String) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
