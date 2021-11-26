package com.fitnest.android.view.registration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.style.*
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40

@Composable
fun RegistrationScreen(navController: NavController, stepName: String) {
    val focusManager = LocalFocusManager.current

    Scaffold {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
        ) {
            val (textTopLabel, textBottomLabel, tfFirstName, tfLastName, tfEmail, tfPassword) = createRefs()
            Text(
                text = "Hey there,",
                modifier = Modifier.constrainAs(textTopLabel) {
                    top.linkTo(parent.top, margin = Padding40)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                style = PoppinsNormalStyle16Black
            )
            Text(
                text = "Create an Account",
                modifier = Modifier
                    .constrainAs(textBottomLabel) {
                        top.linkTo(textTopLabel.bottom, margin = 0.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                style = PoppinsBoldStyle20Black
            )
            OutlinedTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tfFirstName) {
                        top.linkTo(textBottomLabel.bottom, margin = Padding30)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BorderColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = BrandColor,
                    focusedLabelColor = BrandColor,
                ),
                label = { Text("First Name", style = PoppinsNormalStyle14) },
                shape = RoundedCornerShape(14.dp),
                onValueChange = { },
            )
            OutlinedTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tfLastName) {
                        top.linkTo(tfFirstName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BorderColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = BrandColor,
                    focusedLabelColor = BrandColor,
                ),
                label = { Text("Last Name", style = PoppinsNormalStyle14) },
                shape = RoundedCornerShape(14.dp),
                onValueChange = { }
            )
            OutlinedTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tfEmail) {
                        top.linkTo(tfLastName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BorderColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = BrandColor,
                    focusedLabelColor = BrandColor,
                ),
                label = { Text("Email", style = PoppinsNormalStyle14) },
                shape = RoundedCornerShape(14.dp),
                onValueChange = { }
            )
            OutlinedTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tfPassword) {
                        top.linkTo(tfEmail.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BorderColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = BrandColor,
                    focusedLabelColor = BrandColor,
                ),
                label = { Text("Password", style = PoppinsNormalStyle14) },
                shape = RoundedCornerShape(14.dp),
                onValueChange = { }
            )
//            Checkbox(checked = true, onCheckedChange = { })
//            Button(onClick = {}) {}
        }
    }
}
