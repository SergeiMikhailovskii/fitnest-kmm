package com.fitnest.android.screen.registration.create_account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.extension.stringResourceByIdentifier
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.*
import com.fitnest.android.style.Dimen.Dimen1
import com.fitnest.android.style.Dimen.Dimen14
import com.fitnest.android.style.Dimen.Dimen20
import com.fitnest.android.style.Dimen.Dimen50
import com.fitnest.android.style.Padding.Padding0
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding20
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Composable
fun CreateAccountRegistrationScreen(
    navController: NavController,
) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val googleSignInService: GoogleSignInService by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = CreateAccountRegistrationViewModel::class.java
    )

    val focusManager = LocalFocusManager.current

    val screenData by viewModel.screenDataFlow.collectAsState()

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
        viewModel.initializeStartData()
    }

    val loginAnnotatedText = buildAnnotatedString {
        val str = stringResource(id = R.string.registration_create_account_login)
        val loginSpan = stringResource(id = R.string.registration_create_account_login_span)
        val startIndex = str.indexOf(loginSpan)
        val endIndex = startIndex + loginSpan.length
        append(str)
        addStyle(
            style = SpanStyle(color = SecondaryColor1),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = CreateAccountRegistrationScreenUtils.LOGIN_SPAN_TAG,
            annotation = "",
            start = startIndex,
            end = endIndex
        )
    }

    Scaffold {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    focusManager.clearFocus()
                },
        ) {
            val (
                textTopLabel,
                textBottomLabel,
                tfFirstName,
                tfLastName,
                tfEmail,
                tfPassword,
                btnNext,
                tvHaveAccount,
                cvGoogle,
                cvFacebook,
                divider
            ) = createRefs()

            val guidelineHalf =
                createGuidelineFromStart(CreateAccountRegistrationScreenUtils.GUIDELINE_CENTER_PERCENTAGE)

            Text(
                text = stringResource(id = R.string.registration_create_account_title),
                modifier = Modifier.constrainAs(textTopLabel) {
                    top.linkTo(parent.top, margin = Padding40)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                style = PoppinsNormalStyle16Black
            )
            Text(
                text = stringResource(id = R.string.registration_create_account_subtitle),
                modifier = Modifier
                    .constrainAs(textBottomLabel) {
                        top.linkTo(textTopLabel.bottom, margin = Padding0)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                style = PoppinsBoldStyle20Black
            )
            RegistrationOutlinedTextField(
                value = screenData.firstName.orEmpty(),
                constraintAsModifier = {
                    constrainAs(tfFirstName) {
                        top.linkTo(textBottomLabel.bottom, margin = Padding30)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = {
                    Text(
                        stringResource(id = R.string.registration_create_account_first_name_label),
                        style = PoppinsNormalStyle14
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_login),
                        contentDescription = null
                    )
                },
                onValueChange = viewModel::updateFirstName,
                onFocusChanged = viewModel::updateFirstNameFocus,
                isFocused = screenData.isFirstNameFocused,
                error = screenData.exception.firstNameError
            )
            RegistrationOutlinedTextField(
                value = screenData.lastName.orEmpty(),
                constraintAsModifier = {
                    constrainAs(tfLastName) {
                        top.linkTo(tfFirstName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = {
                    Text(
                        stringResource(id = R.string.registration_create_account_last_name_label),
                        style = PoppinsNormalStyle14
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_login),
                        contentDescription = null
                    )
                },
                onValueChange = viewModel::updateLastName,
                onFocusChanged = viewModel::updateLastNameFocus,
                isFocused = screenData.isLastNameFocused,
                error = screenData.exception.lastNameError
            )
            RegistrationOutlinedTextField(
                value = screenData.email.orEmpty(),
                constraintAsModifier = {
                    constrainAs(tfEmail) {
                        top.linkTo(tfLastName.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = {
                    Text(
                        stringResource(id = R.string.registration_create_account_email_label),
                        style = PoppinsNormalStyle14
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null
                    )
                },
                onValueChange = viewModel::updateEmail,
                onFocusChanged = viewModel::updateEmailFocus,
                isFocused = screenData.isEmailFocused,
                error = screenData.exception.emailError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            RegistrationOutlinedTextField(
                value = screenData.password.orEmpty(),
                constraintAsModifier = {
                    constrainAs(tfPassword) {
                        top.linkTo(tfEmail.bottom, margin = Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
                label = {
                    Text(
                        stringResource(id = R.string.registration_create_account_password_label),
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
                trailingIcon = {
                    IconButton(onClick = viewModel::changePasswordVisibility) {
                        val painter =
                            if (screenData.passwordVisible) painterResource(id = R.drawable.ic_password_show)
                            else painterResource(id = R.drawable.ic_password_hide)
                        Image(painter = painter, null)
                    }
                },
                visualTransformation = getPasswordVisualTransformation(!screenData.passwordVisible),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onFocusChanged = viewModel::updatePasswordFocus,
                isFocused = screenData.isPasswordFocused,
                error = screenData.exception.passwordError
            )
            Button(
                onClick = viewModel::submitRegistration,
                shape = CircleShape,
                modifier = Modifier
                    .constrainAs(btnNext) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(divider.top)
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
                    text = stringResource(id = R.string.registration_create_account_next_button_label),
                    style = PoppinsBoldStyle16
                )
            }
            DividerWithChild(
                modifier = Modifier
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(cvGoogle.top)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30,
                        bottom = Padding20
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.registration_create_account_divider_label),
                    modifier = Modifier.padding(horizontal = Padding15),
                    style = PoppinsNormalStyle12Black
                )
            }
            Card(
                modifier = Modifier
                    .height(Dimen50)
                    .width(Dimen50)
                    .constrainAs(cvGoogle) {
                        bottom.linkTo(tvHaveAccount.top, Padding30)
                        end.linkTo(guidelineHalf, Padding15)
                    }
                    .clickable {
                        googleSignInService.login {
                            viewModel.handleGoogleSignIn(it)
                        }
                    },
                shape = RoundedCornerShape(Dimen14),
                border = BorderStroke(Dimen1, GrayColor3),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(Dimen20),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                    )
                }
            }

            Card(
                modifier = Modifier
                    .height(Dimen50)
                    .width(Dimen50)
                    .constrainAs(cvFacebook) {
                        start.linkTo(guidelineHalf, Padding15)
                        bottom.linkTo(tvHaveAccount.top, Padding30)
                    },
                shape = RoundedCornerShape(Dimen14),
                border = BorderStroke(Dimen1, GrayColor3),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(Dimen20),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = null,
                    )
                }
            }
            ClickableText(
                loginAnnotatedText,
                onClick = {
                    loginAnnotatedText.getStringAnnotations(
                        CreateAccountRegistrationScreenUtils.LOGIN_SPAN_TAG,
                        it,
                        it
                    ).firstOrNull()?.let {
                        viewModel.navigateToLogin()
                    }
                },
                modifier = Modifier
                    .constrainAs(tvHaveAccount) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30,
                        bottom = Padding40
                    )
            )
        }
    }
}

@Composable
fun DividerWithChild(modifier: Modifier, child: @Composable () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(GrayColor3)
        )
        child()
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(GrayColor3)
        )
    }
}

@Composable
fun RegistrationOutlinedTextField(
    value: String,
    constraintAsModifier: (Modifier.() -> Modifier)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    isFocused: Boolean = false,
    error: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .run {
                constraintAsModifier?.invoke(this) ?: this
            }
            .padding(
                start = Padding30,
                end = Padding30
            )
            .onFocusChanged {
                onFocusChanged?.invoke(it.isFocused)
            },
    ) {
        val backgroundColor = when {
            isFocused -> Color.White
            else -> BorderColor
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = BrandColor,
                focusedLabelColor = BrandColor,
            ),
            leadingIcon = leadingIcon,
            label = label,
            shape = RoundedCornerShape(Dimen14),
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            isError = error != null,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
        )
        if (error != null) {
            Box(Modifier.height(4.dp))
            Text(stringResourceByIdentifier(error), style = ErrorStyle)
        }
    }

}

private fun handleSignInResult(
    account: GoogleSignInAccount,
    viewModel: CreateAccountRegistrationViewModel
) {
    account.email?.let(viewModel::updateEmail)
    account.idToken?.let(viewModel::updatePassword)
    viewModel.submitRegistration()
}

fun getPasswordVisualTransformation(passwordVisibility: Boolean) =
    if (passwordVisibility) PasswordVisualTransformation()
    else VisualTransformation.None

object CreateAccountRegistrationScreenUtils {
    const val LOGIN_SPAN_TAG = "LOGIN"
    const val GUIDELINE_CENTER_PERCENTAGE = .5F
}
