package com.fitnest.android.screen.registration.complete_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.extension.enum.fromLocalizedName
import com.fitnest.android.extension.enum.localizedNameId
import com.fitnest.android.extension.enum.localizedNames
import com.fitnest.android.style.*
import com.fitnest.android.style.Padding.Padding10
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.domain.enum.SexType
import org.kodein.di.compose.rememberInstance

@ExperimentalMaterialApi
@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun CompleteAccountRegistrationScreenPreview(
) {
    CompleteAccountRegistrationScreen(NavController(LocalContext.current))
}

@ExperimentalMaterialApi
@Composable
fun CompleteAccountRegistrationScreen(
    navController: NavController,
) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = CompleteAccountRegistrationViewModel::class.java,
    )

    val focusManager = LocalFocusManager.current

    val screenData by viewModel.screenDataFlow.collectAsState()

    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        val (imageTop, textStepTitle, textStepDescription, sexDropdown) = createRefs()

        Image(
            painter = painterResource(
                id = R.drawable.ic_registration_complete_account
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        )
        Text(
            "Let’s complete your profile",
            style = PoppinsBoldStyle20Black,
            modifier = Modifier.constrainAs(textStepTitle) {
                top.linkTo(imageTop.bottom, Padding30)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            "It will help us to know more about you!",
            style = PoppinsNormalStyle12Gray1,
            modifier = Modifier.constrainAs(textStepDescription) {
                top.linkTo(textStepTitle.bottom, Padding10)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        SexDropdown(
            modifier = Modifier
                .padding(start = Padding30, end = Padding30)
                .constrainAs(sexDropdown) {
                    top.linkTo(textStepDescription.bottom, Padding30)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onItemClicked = {
                viewModel.saveSex(SexType.fromLocalizedName(it, context))
            },
            value = screenData.sex?.localizedNameId?.let(context::getString) ?: "",
            isFocused = screenData.isSexFocused,
            onFocusChanged = viewModel::updateSexFocus
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun SexDropdown(
    modifier: Modifier,
    onItemClicked: (String) -> Unit,
    value: String,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val sexList by remember { mutableStateOf(SexType.localizedNames(context)) }

    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onFocusChanged(!expanded)
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isFocused) Color.White else BorderColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = BrandColor,
                focusedLabelColor = BrandColor,
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_complete_registration_sex),
                    contentDescription = null
                )
            },
            trailingIcon = { Icon(icon, null) },
            shape = RoundedCornerShape(Dimen.Dimen14),
            label = {
                Text(
                    "Choose Gender",
                    style = PoppinsNormalStyle14
                )
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onFocusChanged(false)
                expanded = false
            },
            modifier = Modifier
                .background(Color.White)
                .exposedDropdownSize()
        ) {
            sexList.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemClicked(it)
                        onFocusChanged(false)
                        expanded = false
                    },
                ) {
                    Text(it, style = PoppinsNormalStyle12Gray2)
                }
            }
        }
    }
}