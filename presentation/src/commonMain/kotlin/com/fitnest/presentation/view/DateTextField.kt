package com.fitnest.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.fitnest.presentation.MR
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DateTextField(
    title: StringResource,
    modifier: Modifier = Modifier,
    value: String,
    error: String?,
    onClick: () -> Unit
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
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { onClick() }
        },
        interactionSource = interactionSource,
        leadingIcon = {
            Image(
                painter = painterResource(MR.images.ic_complete_registration_calendar),
                contentDescription = null
            )
        },
        label = {
            Text(
                stringResource(title),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        readOnly = true,
        error = error,
        enabled = false
    )
}
