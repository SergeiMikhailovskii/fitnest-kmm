package com.fitnest.android.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.di.loginScreenModule
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.internal.FacebookService
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.view.dialog.ForgetPasswordSuccessDialog
import com.fitnest.android.view.ui_elements.DividerWithChild
import com.fitnest.android.view.ui_elements.FitnestTextField
import com.fitnest.android.view.ui_elements.getPasswordVisualTransformation
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

internal object LoginScreenConsts {
    internal const val LOGIN_SPAN_TAG = "1000"
}

@ExperimentalMaterial3Api
@Composable
internal fun LoginScreen(navController: NavController) = subDI(diBuilder = {
    import(loginScreenModule)
}) {
    val focusManager = LocalFocusManager.current

    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewMapper: LoginViewMapper by rememberInstance()
    val registrationAnnotatedString = viewMapper.getLoginAnnotatedString()
    val googleSignInService: GoogleSignInService by rememberInstance()
    val facebookService: FacebookService by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = LoginViewModel::class.java
    )
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val screenData: LoginScreenData by viewModel.screenDataFlow.collectAsState()
    val progress: Boolean by viewModel.progressSharedFlow.collectAsState(false)
    val screenState: LoginScreenState by viewModel.screenStateFlow.collectAsState()

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { focusManager.clearFocus() }
                }
        ) {
            Text(
                modifier = Modifier.padding(top = Padding.Padding40),
                text = stringResource(id = R.string.login_title),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(top = Padding.Padding5),
                text = stringResource(id = R.string.login_description),
                style = MaterialTheme.typography.titleMedium
            )
            FitnestTextField(
                value = screenData.login.orEmpty(),
                modifier = Modifier.padding(
                    top = Padding.Padding30,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.login_email_hint),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                onValueChange = viewModel::updateLogin,
                error = screenData.exception?.loginError
            )
            FitnestTextField(
                value = screenData.password.orEmpty(),
                modifier = Modifier.padding(
                    top = Padding.Padding15,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.login_password_hint),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingIcon = {
                    IconButton(onClick = viewModel::changePasswordVisibility) {
                        val painter =
                            if (screenData.isPasswordVisible) painterResource(id = R.drawable.ic_password_show)
                            else painterResource(id = R.drawable.ic_password_hide)
                        Image(painter = painter, null)
                    }
                },
                onValueChange = viewModel::updatePassword,
                visualTransformation = getPasswordVisualTransformation(screenData.isPasswordVisible),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                error = screenData.exception?.passwordError
            )
            Text(
                text = stringResource(id = R.string.login_forgot_password),
                style = MaterialTheme.typography.bodySmall.copy(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .padding(top = Padding.Padding10)
                    .clickable(onClick = viewModel::forgetPassword)
            )
            Box(modifier = Modifier.weight(1F))
            Button(
                onClick = viewModel::validateAndLogin,
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
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
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
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Row {
                Card(
                    modifier = Modifier
                        .height(Dimen.Dimen50)
                        .width(Dimen.Dimen50)
                        .clickable { googleSignInService.login(viewModel::handleGoogleSignIn) },
                    shape = RoundedCornerShape(Dimen.Dimen14),
                    border = BorderStroke(Dimen.Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .size(Dimen.Dimen20),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = null,
                        )
                    }
                }

                Box(modifier = Modifier.width(Dimen.Dimen30))

                Card(
                    modifier = Modifier
                        .height(Dimen.Dimen50)
                        .width(Dimen.Dimen50),
                    shape = RoundedCornerShape(Dimen.Dimen14),
                    border = BorderStroke(Dimen.Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .size(Dimen.Dimen20)
                            .clickable { facebookService.login(viewModel::handleFacebookLogin) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = null,
                        )
                    }
                }
            }
            ClickableText(
                text = registrationAnnotatedString,
                style = MaterialTheme.typography.bodyMedium,
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

        if (progress) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (screenState == LoginScreenState.FORGET_PASSWORD_SUCCESS) {
            ForgetPasswordSuccessDialog(viewModel::dismissForgetPasswordDialog)
        }
    }
}
