package com.fitnest.android.view.ui_elements

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
    constraintAsModifier: (Modifier.() -> Modifier)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    isFocused: Boolean = false,
    error: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .run {
                constraintAsModifier?.invoke(this) ?: this
            }
            .padding(
                start = Padding.Padding30,
                end = Padding.Padding30
            )
            .onFocusChanged {
                onFocusChanged?.invoke(it.isFocused)
            },
    ) {
        val backgroundColor = when {
            isFocused -> Color.White
            else -> MaterialTheme.colorScheme.surfaceVariant
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = backgroundColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
            leadingIcon = leadingIcon,
            label = label,
            shape = MaterialTheme.shapes.medium,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            isError = error != null,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
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