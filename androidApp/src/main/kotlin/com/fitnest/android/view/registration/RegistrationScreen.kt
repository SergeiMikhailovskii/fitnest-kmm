package com.fitnest.android.view.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40
import com.fitnest.android.style.Padding.Padding5
import com.fitnest.android.style.PoppinsBoldStyle20Black
import com.fitnest.android.style.PoppinsNormalStyle16Black

@Composable
fun RegistrationScreen(navController: NavController, stepName: String) {
    Scaffold {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth(),
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
                    }.background(Color.Red),
                style = PoppinsBoldStyle20Black
            )
            TextField(
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
                onValueChange = { }
            )
            TextField(
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
                onValueChange = { }
            )
            TextField(
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
                onValueChange = { }
            )
            TextField(
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
                onValueChange = { }
            )
//            Checkbox(checked = true, onCheckedChange = { })
//            Button(onClick = {}) {}
        }
    }
}
