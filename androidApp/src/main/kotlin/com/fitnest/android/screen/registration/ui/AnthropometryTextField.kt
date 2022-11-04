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
import androidx.compose.ui.unit.dp
import com.fitnest.android.R
import com.fitnest.android.style.PoppinsNormalStyle14
import com.fitnest.android.style.SecondaryGradient
import com.fitnest.android.view.ui_elements.FitnestTextField

@ExperimentalMaterial3Api
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

@ExperimentalMaterial3Api
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
                .padding(end = 15.dp),
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
                    style = PoppinsNormalStyle14
                )
            },
            readOnly = true,
            error = error
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .aspectRatio(1F)
                .align(Alignment.Bottom)
                .clip(RoundedCornerShape(14.dp))
                .background(Brush.horizontalGradient(SecondaryGradient))
        ) {
            Text(optionLabel, modifier = Modifier.align(Alignment.Center), color = Color.White)
        }

    }
}
