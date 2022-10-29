package com.fitnest.android.screen.registration.create_account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.internal.FacebookService
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Dimen.Dimen1
import com.fitnest.android.style.Dimen.Dimen14
import com.fitnest.android.style.Dimen.Dimen20
import com.fitnest.android.style.Dimen.Dimen50
import com.fitnest.android.style.GrayColor3
import com.fitnest.android.style.Padding.Padding0
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding20
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40
import com.fitnest.android.style.PoppinsBoldStyle16
import com.fitnest.android.style.PoppinsBoldStyle20Black
import com.fitnest.android.style.PoppinsNormalStyle12Black
import com.fitnest.android.style.PoppinsNormalStyle14
import com.fitnest.android.style.PoppinsNormalStyle16Black
import com.fitnest.android.style.SecondaryColor1
import com.fitnest.android.view.ui_elements.FitnestTextField
import com.fitnest.android.view.ui_elements.getPasswordVisualTransformation
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@ExperimentalMaterial3Api
@Composable
@Preview
internal fun CreateAccountRegistrationScreenPreview() {
    CreateAccountRegistrationScreen(navController = NavController(LocalContext.current))
}

@ExperimentalMaterial3Api
@Composable
internal fun CreateAccountRegistrationScreen(navController: NavController) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val googleSignInService: GoogleSignInService by rememberInstance()
    val facebookSignInService: FacebookService by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

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
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
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
        FitnestTextField(
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
        FitnestTextField(
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
        FitnestTextField(
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
        FitnestTextField(
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
                }
                .clickable {
                    facebookSignInService.login {
                        viewModel.handleFacebookSignIn(it)
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

object CreateAccountRegistrationScreenUtils {
    const val LOGIN_SPAN_TAG = "LOGIN"
    const val GUIDELINE_CENTER_PERCENTAGE = .5F
}
