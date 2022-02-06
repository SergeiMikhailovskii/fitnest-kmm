package com.fitnest.android.screen.registration.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fitnest.android.style.*

@Composable
fun AnthropometryTextField(
    value: String,
    modifier: Modifier = Modifier,
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
            value = value,
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
