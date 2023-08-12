package com.fitnest.android.screen.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
import com.fitnest.presentation.R as MR
import com.fitnest.presentation.view.FitnestTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateOfBirthTextField(
    modifier: Modifier = Modifier,
    value: String,
    error: String?,
    onClick: () -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onClick()
    }

    FitnestTextField(
        value = value,
        onValueChange = {},
        modifier = modifier,
        interactionSource = interactionSource,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_complete_registration_calendar),
                contentDescription = null
            )
        },
        label = {
            Text(
                LocalContext.current.getString(MR.string.registration_complete_account_date_of_birth),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        readOnly = true,
        error = error
    )

}
