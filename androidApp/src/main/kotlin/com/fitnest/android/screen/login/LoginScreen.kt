package com.fitnest.android.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.screen.registration.create_account.CreateAccountRegistrationScreenUtils
import com.fitnest.android.screen.registration.create_account.DividerWithChild
import com.fitnest.android.screen.registration.create_account.RegistrationOutlinedTextField
import com.fitnest.android.screen.registration.create_account.getPasswordVisualTransformation
import com.fitnest.android.screen.splash.handleNavigation
import com.fitnest.android.style.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

internal object LoginScreenConsts {
    internal const val LOGIN_SPAN_TAG = "1000"
}

@Composable
internal fun LoginScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current

    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewMapper: LoginViewMapper by rememberInstance()
    val registrationAnnotatedString = viewMapper.getLoginAnnotatedString()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = LoginViewModel::class.java
    )

    val screenData: LoginScreenData by viewModel.screenDataFlow.collectAsState()

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
    }

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
                value = screenData.login.orEmpty(),
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
                onValueChange = viewModel::updateLogin,
                onFocusChanged = viewModel::updateLoginFocus,
                isFocused = screenData.hasLoginFocus,
                error = null/*screenData.exception.firstNameError*/
            )
            RegistrationOutlinedTextField(
                value = screenData.password.orEmpty(),
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
                onValueChange = viewModel::updatePassword,
                onFocusChanged = viewModel::updatePasswordFocus,
                isFocused = screenData.hasPasswordFocus,
                error = null,/*screenData.exception.firstNameError*/
                trailingIcon = {
                    IconButton(onClick = viewModel::changePasswordVisibility) {
                        val painter =
                            if (screenData.isPasswordVisible) painterResource(id = R.drawable.ic_password_show)
                            else painterResource(id = R.drawable.ic_password_hide)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_login),
                        contentDescription = null,
                        modifier = Modifier.padding(end = Padding.Padding10)
                    )
                    Text(
                        text = stringResource(id = R.string.login_login_button),
                        style = PoppinsBoldStyle16
                    )
                }
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

            ClickableText(
                text = registrationAnnotatedString,
                style = PoppinsNormalStyle14Black,
                modifier = Modifier.padding(
                    bottom = Padding.Padding40,
                    top = Padding.Padding30
                )
            ) {
                registrationAnnotatedString.getStringAnnotations(
                    LoginScreenConsts.LOGIN_SPAN_TAG,
                    it,
                    it
                ).firstOrNull()?.let {
                    viewModel.goToRegistration()
                }
            }
        }
    }
}
