package com.fitnest.android.view.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40

@Composable
fun RegistrationScreen(navController: NavController, stepName: String) {
    val focusManager = LocalFocusManager.current

    Scaffold {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
        ) {
            val (
                textTopLabel,
                textBottomLabel,
                tfFirstName,
                tfLastName,
                tfEmail,
                tfPassword,
                btnNext
            ) = createRefs()

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
            RegistrationOutlinedTextField(
                constraintAsModifier = {
                    constrainAs(tfFirstName) {
                        top.linkTo(textBottomLabel.bottom, margin = Padding30)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = { Text("First Name", style = PoppinsNormalStyle14) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_login),
                        contentDescription = null
                    )
                },
            )
            RegistrationOutlinedTextField(
                constraintAsModifier = {
                    constrainAs(tfLastName) {
                        top.linkTo(tfFirstName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = { Text("Last Name", style = PoppinsNormalStyle14) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_login),
                        contentDescription = null
                    )
                },
            )
            RegistrationOutlinedTextField(
                constraintAsModifier = {
                    constrainAs(tfEmail) {
                        top.linkTo(tfLastName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = { Text("Email", style = PoppinsNormalStyle14) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null
                    )
                },
            )
            RegistrationOutlinedTextField(
                constraintAsModifier = {
                    constrainAs(tfPassword) {
                        top.linkTo(tfEmail.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = { Text("Password", style = PoppinsNormalStyle14) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
            )
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .constrainAs(btnNext) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30,
                        bottom = Padding40
                    )
                    .height(Dimen.Dimen60)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.splash_button_title),
                    style = PoppinsBoldStyle16
                )
            }
//            Checkbox(checked = true, onCheckedChange = { })
//            Button(onClick = {}) {}
        }
    }
}

@Composable
fun RegistrationOutlinedTextField(
    constraintAsModifier: Modifier.() -> Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = "",
        modifier = Modifier
            .fillMaxWidth()
            .constraintAsModifier()
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
        leadingIcon = leadingIcon,
        label = label,
        shape = RoundedCornerShape(14.dp),
        onValueChange = { },
    )
}
