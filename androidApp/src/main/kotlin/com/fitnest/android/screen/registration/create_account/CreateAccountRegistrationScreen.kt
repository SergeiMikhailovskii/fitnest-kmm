package com.fitnest.android.screen.registration.create_account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.internal.FacebookService
import com.fitnest.android.internal.GoogleSignInService
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.BodyLargeOnBackground
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Dimen.Dimen1
import com.fitnest.android.style.Dimen.Dimen14
import com.fitnest.android.style.Dimen.Dimen50
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding20
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40
import com.fitnest.android.style.TitleMediumOnBackground
import com.fitnest.android.view.ui_elements.DividerWithChild
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
            style = SpanStyle(color = MaterialTheme.colorScheme.tertiary),
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            }
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.registration_create_account_title),
            modifier = Modifier.padding(top = Padding40),
            style = BodyLargeOnBackground
        )
        Text(
            text = stringResource(id = R.string.registration_create_account_subtitle),
            style = TitleMediumOnBackground
        )
        FitnestTextField(
            value = screenData.firstName.orEmpty(),
            modifier = Modifier.padding(
                top = Padding30,
                start = Padding30,
                end = Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_user_login),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.registration_create_account_first_name_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = viewModel::updateFirstName,
            error = screenData.exception.firstNameError
        )
        FitnestTextField(
            value = screenData.lastName.orEmpty(),
            modifier = Modifier.padding(
                top = Padding15,
                start = Padding30,
                end = Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_user_login),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.registration_create_account_last_name_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = viewModel::updateLastName,
            error = screenData.exception.lastNameError
        )
        FitnestTextField(
            value = screenData.email.orEmpty(),
            modifier = Modifier.padding(
                top = Padding15,
                start = Padding30,
                end = Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.registration_create_account_email_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = viewModel::updateEmail,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            error = screenData.exception.emailError
        )
        FitnestTextField(
            value = screenData.password.orEmpty(),
            modifier = Modifier.padding(
                top = Padding15,
                start = Padding30,
                end = Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null
                )
            },
            label = { Text(stringResource(id = R.string.registration_create_account_password_label)) },
            trailingIcon = {
                IconButton(onClick = viewModel::changePasswordVisibility) {
                    val painter =
                        if (screenData.passwordVisible) painterResource(id = R.drawable.ic_password_show)
                        else painterResource(id = R.drawable.ic_password_hide)
                    Image(painter = painter, null)
                }
            },
            onValueChange = viewModel::updatePassword,
            visualTransformation = getPasswordVisualTransformation(!screenData.passwordVisible),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            error = screenData.exception.passwordError
        )
        Button(
            onClick = viewModel::submitRegistration,
            shape = CircleShape,
            modifier = Modifier
                .padding(all = Padding30)
                .height(Dimen.Dimen60)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.registration_create_account_next_button_label),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        DividerWithChild(
            modifier = Modifier
                .padding(
                    start = Padding30,
                    end = Padding30,
                    bottom = Padding20
                )
        ) {
            Text(
                text = stringResource(id = R.string.registration_create_account_divider_label),
                modifier = Modifier.padding(horizontal = Padding15),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Row {
            Card(
                modifier = Modifier
                    .padding(end = Padding15)
                    .height(Dimen50)
                    .width(Dimen50)
                    .clickable {
                        googleSignInService.login(viewModel::handleGoogleSignIn)
                    },
                shape = RoundedCornerShape(Dimen14),
                border = BorderStroke(Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
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
                    .padding(start = Padding15)
                    .height(Dimen50)
                    .width(Dimen50)
                    .clickable {
                        facebookSignInService.login(viewModel::handleFacebookSignIn)
                    },
                shape = RoundedCornerShape(Dimen14),
                border = BorderStroke(Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
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
                .padding(
                    start = Padding30,
                    end = Padding30,
                    bottom = Padding40,
                    top = Padding30
                )
        )
    }
}

private object CreateAccountRegistrationScreenUtils {
    const val LOGIN_SPAN_TAG = "LOGIN"
}
