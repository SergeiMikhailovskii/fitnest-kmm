package com.fitnest.presentation.decompose.registration.steps.createAccount

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.fitnest.presentation.MR
import com.fitnest.presentation.screen.registration.createAccount.CreateAccountRegistrationScreenUtils
import com.fitnest.presentation.style.BodyLargeOnBackground
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.style.TitleMediumOnBackground
import com.fitnest.presentation.view.DividerWithChild
import com.fitnest.presentation.view.FitnestTextField
import com.fitnest.presentation.view.getPasswordVisualTransformation
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountRegistrationView(component: CreateAccountRegistrationComponent) {
    val focusManager = LocalFocusManager.current
    val model by component.model.subscribeAsState()
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
            modifier = Modifier.padding(top = Padding.Padding40),
            style = BodyLargeOnBackground
        )
        Text(
            text = stringResource(MR.strings.registration_create_account_subtitle),
            style = TitleMediumOnBackground
        )
        FitnestTextField(
            value = model.firstName,
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(MR.images.ic_user_login),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_first_name_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = {
//                viewModel::updateFirstName
            }
//            error = screenData.exception.firstNameError
        )
        FitnestTextField(
            value = model.lastName,
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(MR.images.ic_user_login),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_last_name_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = {
//                viewModel::updateLastName
            }
//            error = screenData.exception.lastNameError
        )
        FitnestTextField(
            value = model.email,
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(MR.images.ic_email),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(MR.strings.registration_create_account_email_label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onValueChange = {
//                viewModel::updateEmail
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
//            error = screenData.exception.emailError
        )
        FitnestTextField(
            value = model.password,
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(MR.images.ic_lock),
                    contentDescription = null
                )
            },
            label = { Text(stringResource(MR.strings.registration_create_account_password_label)) },
            trailingIcon = {
                IconButton(onClick = {
//                    viewModel::changePasswordVisibility
                }) {
                    val painter =
                        if (model.isPasswordVisible) {
                            painterResource(MR.images.ic_password_show)
                        } else {
                            painterResource(MR.images.ic_password_hide)
                        }
                    Image(painter = painter, null)
                }
            },
            onValueChange = {
//                viewModel::updatePassword
            },
            visualTransformation = getPasswordVisualTransformation(!model.isPasswordVisible),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            error = screenData.exception.passwordError
        )
        Button(
            onClick = {
//                viewModel.submitRegistration()
            },
            shape = CircleShape,
            modifier = Modifier
                .padding(all = Padding.Padding30)
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
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding20
                )
        ) {
            Text(
                text = stringResource(MR.strings.registration_create_account_divider_label),
                modifier = Modifier.padding(horizontal = Padding.Padding15),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Row {
            Card(
                modifier = Modifier
                    .padding(end = Padding.Padding15)
                    .height(Dimen.Dimen50)
                    .width(Dimen.Dimen50)
                    .clickable {
//                        googleSignInService.login(viewModel::handleGoogleSignIn)
                    },
                shape = RoundedCornerShape(Dimen.Dimen14),
                border = BorderStroke(Dimen.Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(MR.images.ic_google),
                        contentDescription = null
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(start = Padding.Padding15)
                    .height(Dimen.Dimen50)
                    .width(Dimen.Dimen50)
                    .clickable {
//                        facebookSignInService.login(viewModel::handleFacebookSignIn)
                    },
                shape = RoundedCornerShape(Dimen.Dimen14),
                border = BorderStroke(Dimen.Dimen1, MaterialTheme.colorScheme.surfaceVariant),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(MR.images.ic_facebook),
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
//                    viewModel.navigateToLogin()
                }
            },
            modifier = Modifier
                .padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding40,
                    top = Padding.Padding30
                )
        )
    }
}
