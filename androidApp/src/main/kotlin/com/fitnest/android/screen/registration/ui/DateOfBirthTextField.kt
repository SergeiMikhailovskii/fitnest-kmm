package com.fitnest.android.screen.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
import com.fitnest.android.style.BorderColor
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.PoppinsNormalStyle14

@Composable
fun DateOfBirthTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onClick()
    }

    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = modifier,
        interactionSource = interactionSource,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BorderColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = BrandColor,
            focusedLabelColor = BrandColor,
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_complete_registration_calendar),
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(Dimen.Dimen14),
        label = {
            Text(
                LocalContext.current.getString(R.string.registration_complete_account_date_of_birth),
                style = PoppinsNormalStyle14
            )
        },
        readOnly = true,
        isError = isError
    )

}
