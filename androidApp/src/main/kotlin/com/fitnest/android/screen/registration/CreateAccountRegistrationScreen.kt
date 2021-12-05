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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
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
        val str = "Already have an account? Login"
        val startIndex = str.indexOf("Login")
        val endIndex = startIndex + "Login".length
        append(str)
        addStyle(
            style = SpanStyle(color = SecondaryColor1),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "LOGIN",
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

            val guidelineHalf = createGuidelineFromStart(.5F)

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
                value = screenData.firstName,
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
                label = { Text("Last Name", style = PoppinsNormalStyle14) },
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
                label = { Text("Email", style = PoppinsNormalStyle14) },
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
                label = { Text("Password", style = PoppinsNormalStyle14) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
                onValueChange = viewModel::updatePassword,
                trailingIcon = {
                    IconButton(onClick = { viewModel.changePasswordVisibility() }) {
                        val painter =
                            if (screenData.passwordVisible) painterResource(id = R.drawable.ic_password_show)
                            else painterResource(id = R.drawable.ic_password_hide)
                        Image(painter = painter, "")
                    }
                },
                visualTransformation = getPasswordVisualTransformation(!screenData.passwordVisible),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
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
                    .height(50.dp)
                    .width(50.dp)
                    .constrainAs(cvGoogle) {
                        bottom.linkTo(tvHaveAccount.top, 30.dp)
                        end.linkTo(guidelineHalf, 15.dp)
                    },
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, GrayColor3),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "",
                    )
                }
            }

            Card(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .constrainAs(cvFacebook) {
                        start.linkTo(guidelineHalf, 15.dp)
                        bottom.linkTo(tvHaveAccount.top, 30.dp)
                    },
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, GrayColor3),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "",
                    )
                }
            }
            ClickableText(
                loginAnnotatedText,
                onClick = {
                    loginAnnotatedText.getStringAnnotations("LOGIN", it, it)
                        .firstOrNull()?.let {
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
        shape = RoundedCornerShape(14.dp),
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
    )
}

fun getPasswordVisualTransformation(passwordVisibility: Boolean) =
    if (passwordVisibility) PasswordVisualTransformation()
    else VisualTransformation.None
