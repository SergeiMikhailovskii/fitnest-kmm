package com.fitnest.android.screen.registration.complete_account

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.domain.enum.SexType
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance
import java.util.*

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

    val coroutineScope = rememberCoroutineScope()

    val modalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetContent = {
            Box(Modifier.height(400.dp))
        }, sheetState = modalBottomSheetState
    ) {
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
                imageTop,
                textStepTitle,
                textStepDescription,
                sexDropdown,
                inputBirthDate,
                inputWeight,
                optionWeight,
            ) = createRefs()

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
            DateOfBirthTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(inputBirthDate) {
                        top.linkTo(sexDropdown.bottom, Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, value = screenData.formattedDateOfBirth() ?: ""
            ) {
                showDatePicker(context as AppCompatActivity, viewModel::saveBirthDate)
            }
            AnthropometryTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(inputWeight) {
                        top.linkTo(inputBirthDate.bottom, Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                leadingIcon = R.drawable.ic_complete_registration_weight,
                label = "Your Weight",
                optionLabel = "KG"
            ) {
                coroutineScope.launch { modalBottomSheetState.show() }
            }
        }
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

@Composable
fun DateOfBirthTextField(modifier: Modifier, value: String, onClick: () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onClick()
    }

    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = modifier,
        interactionSource = interactionSource,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BorderColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = BrandColor,
            focusedLabelColor = BrandColor,
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_complete_registration_calendar),
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(Dimen.Dimen14),
        label = {
            Text(
                "Date of Birth",
                style = PoppinsNormalStyle14
            )
        },
        readOnly = true,
    )

}

@Composable
fun AnthropometryTextField(
    modifier: Modifier,
    @DrawableRes leadingIcon: Int,
    label: String,
    optionLabel: String,
    onTextFieldClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onTextFieldClick()
    }
    var height by remember { mutableStateOf(0) }
    Row(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .onGloballyPositioned {
                    height = it.size.height
                }
                .padding(end = 15.dp),
            value = "",
            onValueChange = {},
            interactionSource = interactionSource,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BorderColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = BrandColor,
                focusedLabelColor = BrandColor,
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(Dimen.Dimen14),
            label = {
                Text(
                    label,
                    style = PoppinsNormalStyle14
                )
            },
            readOnly = true,
        )
        Box(
            modifier = Modifier
                .width(height.dp)
                .aspectRatio(1F)
                .align(Alignment.Bottom)
                .clip(RoundedCornerShape(14.dp))
                .background(Brush.horizontalGradient(SecondaryGradient))
        ) {
            Text(optionLabel, modifier = Modifier.align(Alignment.Center), color = Color.White)
        }

    }

}

private fun showDatePicker(activity: AppCompatActivity, onDatePicked: (Date) -> Unit) {
    MaterialDatePicker.Builder.datePicker()
        .setTitleText("Date of birth")
        .build()
        .apply {
            addOnPositiveButtonClickListener {
                onDatePicked(Date(it))
            }
            show(activity.supportFragmentManager, toString())
        }
}