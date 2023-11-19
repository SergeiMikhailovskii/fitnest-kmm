package com.fitnest.presentation.decompose.registration.steps.completeAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.extension.dateToString
import com.fitnest.presentation.MR
import com.fitnest.presentation.decompose.dialog.date.DateDialogComponent
import com.fitnest.presentation.dialog.DatePickerDialog
import com.fitnest.presentation.extension.enumType.fromStringResource
import com.fitnest.presentation.extension.enumType.stringResource
import com.fitnest.presentation.extension.enumType.stringResources
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.view.DateTextField
import com.fitnest.presentation.view.FitnestDropdown
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CompleteAccountRegistrationView(component: CompleteAccountRegistrationComponent) {
    val focusManager = LocalFocusManager.current
    val model by component.model.subscribeAsState()
    val dialogSlot by component.dialog.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(MR.images.ic_registration_complete_account),
            contentDescription = null,
            modifier = Modifier
                .weight(1F, fill = false)
        )

        Text(
            stringResource(MR.strings.registration_complete_account_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = Padding.Padding30)
        )

        Text(
            stringResource(MR.strings.registration_complete_account_screen_description),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.padding(top = Padding.Padding10)
        )

        FitnestDropdown(
            values = SexType.stringResources,
            image = MR.images.ic_registration_complete_account_sex,
            title = MR.strings.registration_complete_account_choose_gender,
            modifier = Modifier
                .padding(horizontal = Padding.Padding30)
                .padding(top = Padding.Padding30),
            onItemClicked = {
                val sexType = SexType.fromStringResource(it)
                component.setSex(sexType)
            },
            value = model.sexType?.stringResource?.let { stringResource(it) }.orEmpty(),
            error = model.exception.genderError
        )
        DateTextField(
            title = MR.strings.registration_complete_account_date_of_birth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Padding30)
                .padding(top = Padding.Padding15),
            value = model.dateOfBirth?.dateToString("dd/MM/yyyy").orEmpty(),
            error = model.exception.birthDateError,
            onClick = component::showDateOfBirthPicker
        )
//        AnthropometryTextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = Padding.Padding30, end = Padding.Padding30)
//                .constrainAs(inputWeight) {
//                    bottom.linkTo(inputHeight.top, Padding.Padding15)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                },
//            value = screenData.weight?.toString().orEmpty(),
//            leadingIcon = R.drawable.ic_complete_registration_weight,
//            label = context.getString(MR.string.registration_complete_account_weight_hint),
//            optionLabel = context.getString(MR.string.registration_complete_account_weight_kg),
//            error = screenData.exception.weightError,
//            onTextFieldClick = viewModel::openWeightBottomSheet
//        )
//        AnthropometryTextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = Padding.Padding30, end = Padding.Padding30)
//                .constrainAs(inputHeight) {
//                    bottom.linkTo(btnNext.top, Padding.Padding30)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                },
//            value = screenData.height?.toString().orEmpty(),
//            leadingIcon = R.drawable.ic_complete_registration_height,
//            label = context.getString(MR.string.registration_complete_account_height_hint),
//            optionLabel = context.getString(MR.string.registration_complete_account_height_cm),
//            error = screenData.exception.heightError,
//            onTextFieldClick = viewModel::openHeightBottomSheet
//        )
//        Button(
//            onClick = viewModel::submitRegistration,
//            shape = CircleShape,
//            modifier = Modifier
//                .constrainAs(btnNext) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom)
//                }
//                .padding(
//                    start = Padding.Padding30,
//                    end = Padding.Padding30,
//                    bottom = Padding.Padding40
//                )
//                .height(Dimen.Dimen60)
//                .fillMaxWidth(),
//        ) {
//            Text(
//                text = stringResource(id = MR.string.registration_complete_account_next_button_label),
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Bold
//                )
//            )
//            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
//        }
    }

    dialogSlot.child?.instance?.also {
        if (it is DateDialogComponent) {
            DatePickerDialog(it)
        }
    }
}
