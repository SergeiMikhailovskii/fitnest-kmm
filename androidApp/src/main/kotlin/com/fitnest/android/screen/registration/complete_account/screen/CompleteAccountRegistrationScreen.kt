package com.fitnest.android.screen.registration.complete_account.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.di.RegistrationModule
import com.fitnest.android.extension.enum.fromLocalizedName
import com.fitnest.android.extension.enum.localizedNameId
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.screen.registration.ui.AnthropometryTextField
import com.fitnest.android.screen.registration.ui.DateOfBirthTextField
import com.fitnest.android.screen.registration.ui.SexDropdown
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.style.Padding.Padding10
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.domain.enum.SexType
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import java.util.Date

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
internal fun CompleteAccountRegistrationScreenPreview() {
    CompleteAccountRegistrationScreen(NavController(LocalContext.current))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CompleteAccountRegistrationScreen(
    navController: NavController
) = subDI(diBuilder = {
    import(RegistrationModule.completeAccountRegistrationScreenModule)
}) {
    val di = localDI()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance { di }
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel<CompleteAccountRegistrationViewModel>(
        factory = viewModelFactory
    )

    val focusManager = LocalFocusManager.current

    val screenData by viewModel.screenDataFlow.collectAsState()

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    var modalBottomSheetType by remember {
        mutableStateOf<CompleteAccountRegistrationScreenBottomSheetType?>(null)
    }

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetContent = {
            when (modalBottomSheetType) {
                CompleteAccountRegistrationScreenBottomSheetType.WEIGHT -> {
//                    AnthropometryBottomSheet(
//                        coroutineScope = coroutineScope,
//                        modalBottomSheetState = modalBottomSheetState,
//                        minValue = 0,
//                        maxValue = 200,
//                        initialValue = 70,
//                        onSubmit = viewModel::saveWeight
//                    )
                }
                CompleteAccountRegistrationScreenBottomSheetType.HEIGHT -> {
//                    AnthropometryBottomSheet(
//                        coroutineScope = coroutineScope,
//                        modalBottomSheetState = modalBottomSheetState,
//                        minValue = 0,
//                        maxValue = 220,
//                        initialValue = 188,
//                        onSubmit = viewModel::saveHeight
//                    )
                }
                else -> {
                    Box(Modifier.height(1.dp))
                }
            }
        }, sheetState = modalBottomSheetState
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { focusManager.clearFocus() }
                }
        ) {
            val (
                imageTop,
                textStepTitle,
                textStepDescription,
                sexDropdown,
                inputBirthDate,
                inputWeight,
                inputHeight,
                btnNext,
            ) = createRefs()

            Image(
                painter = painterResource(
                    id = R.drawable.ic_registration_complete_account
                ),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(imageTop) {
                        top.linkTo(parent.top)
                        bottom.linkTo(textStepTitle.top, Padding30)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
            )
            Text(
                context.getString(R.string.registration_complete_account_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.constrainAs(textStepTitle) {
                    bottom.linkTo(textStepDescription.top, Padding10)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                context.getString(R.string.registration_complete_account_screen_description),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.constrainAs(textStepDescription) {
                    bottom.linkTo(sexDropdown.top, Padding30)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            SexDropdown(
                modifier = Modifier
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(sexDropdown) {
                        bottom.linkTo(inputBirthDate.top, Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onItemClicked = {
                    viewModel.saveSex(SexType.fromLocalizedName(it, context))
                },
                value = screenData.sex?.localizedNameId?.let(context::getString).orEmpty(),
                onFocusChanged = viewModel::updateSexFocus,
                error = screenData.exception.genderError
            )
            DateOfBirthTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(inputBirthDate) {
                        bottom.linkTo(inputWeight.top, Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                value = screenData.formattedDateOfBirth().orEmpty(),
                error = screenData.exception.birthDateError
            ) {
                showDatePicker(context as AppCompatActivity, viewModel::saveBirthDate, context)
            }
            AnthropometryTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(inputWeight) {
                        bottom.linkTo(inputHeight.top, Padding15)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                value = screenData.weight?.toString().orEmpty(),
                leadingIcon = R.drawable.ic_complete_registration_weight,
                label = context.getString(R.string.registration_complete_account_weight_hint),
                optionLabel = context.getString(R.string.registration_complete_account_weight_kg),
                error = screenData.exception.weightError
            ) {
//                modalBottomSheetType = CompleteAccountRegistrationScreenBottomSheetType.WEIGHT
//                coroutineScope.launch { modalBottomSheetState.show() }
                viewModel.openWeightBottomSheet()
            }
            AnthropometryTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding30, end = Padding30)
                    .constrainAs(inputHeight) {
                        bottom.linkTo(btnNext.top, Padding30)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                value = screenData.height?.toString().orEmpty(),
                leadingIcon = R.drawable.ic_complete_registration_height,
                label = context.getString(R.string.registration_complete_account_height_hint),
                optionLabel = context.getString(R.string.registration_complete_account_height_cm),
                error = screenData.exception.heightError,
            ) {
                coroutineScope.launch {
                    modalBottomSheetType =
                        CompleteAccountRegistrationScreenBottomSheetType.HEIGHT
                    modalBottomSheetState.show()
                }
            }
            Button(
                onClick = viewModel::submitRegistration,
                shape = CircleShape,
                modifier = Modifier
                    .constrainAs(btnNext) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(
                        start = Padding30,
                        end = Padding30,
                        bottom = Padding.Padding40
                    )
                    .height(Dimen.Dimen60)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.registration_complete_account_next_button_label),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
            }
        }
    }
}

private fun showDatePicker(
    activity: AppCompatActivity,
    onDatePicked: (Date) -> Unit,
    context: Context
) {
    MaterialDatePicker.Builder.datePicker()
        .setTitleText(context.getString(R.string.registration_complete_account_date_of_birth))
        .build()
        .apply {
            addOnPositiveButtonClickListener {
                onDatePicked(Date(it))
            }
            show(activity.supportFragmentManager, toString())
        }
}