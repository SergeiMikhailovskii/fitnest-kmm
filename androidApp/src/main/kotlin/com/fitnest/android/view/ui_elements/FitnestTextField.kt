package com.fitnest.android.view.ui_elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.fitnest.android.extension.stringResourceByIdentifier
import com.fitnest.android.style.ErrorStyle
import com.fitnest.android.style.Padding

@ExperimentalMaterial3Api
@Composable
internal fun FitnestTextField(
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChanged?.invoke(it.isFocused)
            },
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent
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
            interactionSource = interactionSource
        )
        if (error != null) {
            Text(
                stringResourceByIdentifier(error),
                style = ErrorStyle,
                modifier = Modifier.padding(top = Padding.Small)
            )
        }
    }

}

internal fun getPasswordVisualTransformation(passwordVisibility: Boolean) =
    if (passwordVisibility) PasswordVisualTransformation()
    else VisualTransformation.None