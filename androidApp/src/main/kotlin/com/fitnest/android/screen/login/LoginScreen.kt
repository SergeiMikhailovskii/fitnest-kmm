package com.fitnest.android.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.screen.registration.create_account.DividerWithChild
import com.fitnest.android.screen.registration.create_account.RegistrationOutlinedTextField
import com.fitnest.android.screen.registration.create_account.getPasswordVisualTransformation
import com.fitnest.android.style.*

@Composable
internal fun LoginScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }) {
            Text(
                modifier = Modifier.padding(top = Padding.Padding40),
                text = stringResource(id = R.string.login_title),
                style = PoppinsNormalStyle16Black
            )
            Text(
                modifier = Modifier.padding(top = Padding.Padding5),
                text = stringResource(id = R.string.login_description),
                style = PoppinsBoldStyle20Black
            )
            RegistrationOutlinedTextField(
                value = "",
                constraintAsModifier = {
                    Modifier.padding(top = Padding.Padding30)
                },
                label = {
                    Text(
                        stringResource(id = R.string.login_email_hint),
                        style = PoppinsNormalStyle14
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null
                    )
                },
                onValueChange = { /*viewModel::updateFirstName*/ },
                onFocusChanged = { /*viewModel::updateFirstNameFocus*/ },
                isFocused = false/*screenData.isFirstNameFocused*/,
                error = null/*screenData.exception.firstNameError*/
            )
            RegistrationOutlinedTextField(
                value = "",
                constraintAsModifier = {
                    Modifier.padding(top = Padding.Padding15)
                },
                label = {
                    Text(
                        stringResource(id = R.string.login_password_hint),
                        style = PoppinsNormalStyle14
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
                onValueChange = { /*viewModel::updateFirstName*/ },
                onFocusChanged = { /*viewModel::updateFirstNameFocus*/ },
                isFocused = false,/*screenData.isFirstNameFocused*/
                error = null,/*screenData.exception.firstNameError*/
                trailingIcon = {
                    IconButton(onClick = { /*viewModel::changePasswordVisibility*/ }) {
                        val painter =
                            /*if (screenData.passwordVisible)*/
                            painterResource(id = R.drawable.ic_password_show)
//                            else painterResource(id = R.drawable.ic_password_hide)
                        Image(painter = painter, null)
                    }
                },
                visualTransformation = getPasswordVisualTransformation(true),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Text(
                text = stringResource(id = R.string.login_forgot_password),
                style = PoppinsMediumStyle12Gray2.copy(
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.padding(top = Padding.Padding10)
            )
            Box(modifier = Modifier.weight(1F))
            Button(
                onClick = {},
                shape = CircleShape,
                modifier = Modifier
                    .padding(
                        start = Padding.Padding30,
                        end = Padding.Padding30,
                        bottom = Padding.Padding40
                    )
                    .height(Dimen.Dimen60)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.login_login_button),
                    style = PoppinsBoldStyle16
                )
            }
            DividerWithChild(
                modifier = Modifier
                    .padding(
                        start = Padding.Padding30,
                        end = Padding.Padding30,
                        bottom = Padding.Padding20
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.login_footer_divider),
                    modifier = Modifier.padding(horizontal = Padding.Padding15),
                    style = PoppinsNormalStyle12Black
                )
            }

            Text(
                style = PoppinsNormalStyle14Black,
                text = stringResource(id = R.string.login_register),
                modifier = Modifier.padding(
                    bottom = Padding.Padding40,
                    top = Padding.Padding30
                )
            )
        }
    }
}
