package com.fitnest.presentation.screen.registration.createAccount

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.fitnest.presentation.MR
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.internal.FacebookService
import com.fitnest.presentation.internal.GoogleSignInService
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.BodyLargeOnBackground
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Dimen.Dimen1
import com.fitnest.presentation.style.Dimen.Dimen14
import com.fitnest.presentation.style.Dimen.Dimen50
import com.fitnest.presentation.style.Padding.Padding15
import com.fitnest.presentation.style.Padding.Padding20
import com.fitnest.presentation.style.Padding.Padding30
import com.fitnest.presentation.style.Padding.Padding40
import com.fitnest.presentation.style.TitleMediumOnBackground
import com.fitnest.presentation.view.DividerWithChild
import com.fitnest.presentation.view.FitnestTextField
import com.fitnest.presentation.view.getPasswordVisualTransformation
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalResourceApi::class)
@ExperimentalMaterial3Api
@Composable
fun CreateAccountRegistrationScreen(
    viewModel: CreateAccountRegistrationViewModel,
    onNavigate: (Route) -> Unit
) {
    val googleSignInService: GoogleSignInService by rememberInstance()
    val facebookSignInService: FacebookService by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val focusManager = LocalFocusManager.current

    val screenData by viewModel.screenDataFlow.collectAsState()

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect(onNavigate)
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        viewModel.initializeStartData()
    }

    val loginAnnotatedText = buildAnnotatedString {
        val str = stringResource(MR.strings.registration_create_account_login)
        val loginSpan = stringResource(MR.strings.registration_create_account_login_span)
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
            text = stringResource(MR.strings.registration_create_account_title),
            modifier = Modifier.padding(top = Padding40),
            style = BodyLargeOnBackground
        )
        Text(
            text = stringResource(MR.strings.registration_create_account_subtitle),
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
                    painter = painterResource("ic_user_login.xml"),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_first_name_label),
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
                    painter = painterResource("ic_user_login.xml"),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_last_name_label),
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
                    painter = painterResource("ic_email.xml"),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_email_label),
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
                    painter = painterResource("ic_lock.xml"),
                    contentDescription = null
                )
            },
            label = { Text(stringResource(MR.strings.registration_create_account_password_label)) },
            trailingIcon = {
                IconButton(onClick = viewModel::changePasswordVisibility) {
                    val painter =
                        if (screenData.passwordVisible) {
                            painterResource("ic_password_show.xml")
                        } else {
                            painterResource("ic_password_hide.xml")
                        }
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
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(MR.strings.registration_create_account_next_button_label),
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
                text = stringResource(MR.strings.registration_create_account_divider_label),
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
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = dev.icerock.moko.resources.compose.painterResource(MR.images.ic_google),
                        contentDescription = null
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
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource("ic_facebook.xml"),
                        contentDescription = null
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
