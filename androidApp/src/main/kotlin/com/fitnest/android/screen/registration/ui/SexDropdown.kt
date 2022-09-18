package com.fitnest.android.screen.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
import com.fitnest.android.extension.enum.localizedNames
import com.fitnest.android.style.*
import com.fitnest.domain.enum.SexType

@ExperimentalMaterialApi
@Composable
fun SexDropdown(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    value: String,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    isError: Boolean
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val sexList by remember { mutableStateOf(SexType.localizedNames(context)) }

    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onFocusChanged(!expanded)
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isFocused) Color.White else BorderColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = BrandColor,
                focusedLabelColor = BrandColor,
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_complete_registration_sex),
                    contentDescription = null
                )
            },
            trailingIcon = { Icon(icon, null) },
            shape = RoundedCornerShape(Dimen.Dimen14),
            label = {
                Text(
                    context.getString(R.string.registration_complete_account_choose_gender),
                    style = PoppinsNormalStyle14
                )
            },
            isError = isError,
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onFocusChanged(false)
                expanded = false
            },
            modifier = Modifier
                .background(Color.White)
                .exposedDropdownSize()
        ) {
            sexList.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemClicked(it)
                        onFocusChanged(false)
                        expanded = false
                    },
                ) {
                    Text(it, style = PoppinsNormalStyle12Gray2)
                }
            }
        }
    }
}
