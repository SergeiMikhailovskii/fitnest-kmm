package com.fitnest.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.fitnest.presentation.extension.tertiaryGradient
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnthropometryTextField(
    value: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageResource,
    label: StringResource,
    optionLabel: StringResource,
    error: String?,
    onTextFieldClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onTextFieldClick()
    }
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        FitnestTextField(
            value = value,
            modifier = Modifier
                .weight(1F)
                .padding(end = Padding.Padding15),
            onValueChange = {},
            interactionSource = interactionSource,
            leadingIcon = {
                Image(
                    painter = painterResource(leadingIcon),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(label),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            readOnly = true,
            error = error
        )
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .align(Alignment.Bottom)
                .clip(RoundedCornerShape(Dimen.Dimen14))
                .background(Brush.horizontalGradient(MaterialTheme.colorScheme.tertiaryGradient))
                .fillMaxHeight()
        ) {
            Text(stringResource(optionLabel), modifier = Modifier.align(Alignment.Center), color = Color.White)
        }
    }
}
