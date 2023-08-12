package com.fitnest.android.screen.registration.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.extension.tertiaryGradient
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.view.FitnestTextField

@Preview
@Composable
fun AnthropometryTextFieldPreview() {
    AnthropometryTextField(
        value = "",
        leadingIcon = R.drawable.ic_complete_registration_weight,
        label = "Label",
        optionLabel = "CM",
        error = null
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnthropometryTextField(
    value: String,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int,
    label: String,
    optionLabel: String,
    error: String?,
    onTextFieldClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onTextFieldClick()
    }
    Row(modifier = modifier) {
        FitnestTextField(
            value = value,
            modifier = Modifier
                .weight(1F)
                .padding(end = Padding.Padding15),
            onValueChange = {},
            interactionSource = interactionSource,
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    label,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            readOnly = true,
            error = error
        )
        Box(
            modifier = Modifier
                .width(Dimen.Dimen50)
                .aspectRatio(1F)
                .align(Alignment.Bottom)
                .clip(RoundedCornerShape(Dimen.Dimen14))
                .background(Brush.horizontalGradient(MaterialTheme.colorScheme.tertiaryGradient))
        ) {
            Text(optionLabel, modifier = Modifier.align(Alignment.Center), color = Color.White)
        }

    }
}
