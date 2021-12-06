package com.fitnest.android.screen.registration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.fitnest.android.style.Dimen.Dimen1
import com.fitnest.android.style.Dimen.Dimen14
import com.fitnest.android.style.Dimen.Dimen20
import com.fitnest.android.style.Dimen.Dimen50
import com.fitnest.android.style.Padding.Padding0
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.Padding.Padding40
import org.kodein.di.compose.rememberInstance

@Composable
fun CreateAccountRegistrationScreen(navController: NavController, stepName: String) {
    val focusManager = LocalFocusManager.current

    val viewModel: CreateAccountRegistrationViewModel by rememberInstance()

    val screenData by viewModel.screenDataFlow.collectAsState()

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
                btnNext,
                tvHaveAccount,
                cvGoogle,
                cvFacebook,
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
                value = screenData.firstName,
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
                onValueChange = viewModel::updateFirstName
            )
            RegistrationOutlinedTextField(
                value = screenData.lastName,
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
                onValueChange = viewModel::updateLastName
            )
            RegistrationOutlinedTextField(
                value = screenData.email,
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
                onValueChange = viewModel::updateEmail
            )
            RegistrationOutlinedTextField(
                value = screenData.password,
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
            )
            DividerWithText()
            Button(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier
                    .constrainAs(btnNext) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(cvGoogle.top)
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
            Card(
                modifier = Modifier
                    .height(Dimen50)
                    .width(Dimen50)
                    .constrainAs(cvGoogle) {
                        bottom.linkTo(tvHaveAccount.top, Padding30)
                        end.linkTo(guidelineHalf, Padding15)
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

@Preview
@Composable
fun DividerWithText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1F).height(1.dp).background(Color.Red))
        Text(text = "Or")
        Box(modifier = Modifier.weight(1F).height(1.dp).background(Color.Red))
    }
}

@Composable
fun RegistrationOutlinedTextField(
    value: String,
    constraintAsModifier: Modifier.() -> Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        modifier = Modifier
            .fillMaxWidth()
            .constraintAsModifier()
            .padding(
                start = Padding30,
                end = Padding30
            ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BorderColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = BrandColor,
            focusedLabelColor = BrandColor,
        ),
        leadingIcon = leadingIcon,
        label = label,
        shape = RoundedCornerShape(Dimen14),
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
    )
}

fun getPasswordVisualTransformation(passwordVisibility: Boolean) =
    if (passwordVisibility) PasswordVisualTransformation()
    else VisualTransformation.None

object CreateAccountRegistrationScreenUtils {
    const val LOGIN_SPAN_TAG = "LOGIN"
    const val GUIDELINE_CENTER_PERCENTAGE = .5F
}
