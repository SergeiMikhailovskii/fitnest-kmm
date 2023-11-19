package com.fitnest.presentation.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.fitnest.presentation.extension.errorLocalizedValue
import com.fitnest.presentation.style.ErrorStyle
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource

@ExperimentalMaterial3Api
@Composable
fun FitnestTextField(
    value: String,
    modifier: Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    error: String? = null,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChanged?.invoke(it.isFocused)
            }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = leadingIcon,
            label = label,
            shape = MaterialTheme.shapes.medium,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            isError = error != null,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            readOnly = readOnly,
            interactionSource = interactionSource,
            enabled = enabled
        )
        if (error != null) {
            Text(
                stringResource(error.errorLocalizedValue),
                style = ErrorStyle,
                modifier = Modifier.padding(top = Padding.Small)
            )
        }
    }
}

fun getPasswordVisualTransformation(passwordVisibility: Boolean) =
    if (passwordVisibility) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
