package com.fitnest.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnestDropdown(
    values: List<StringResource>,
    image: ImageResource,
    title: StringResource,
    modifier: Modifier = Modifier,
    onItemClicked: (StringResource) -> Unit,
    value: String,
    onFocusChanged: (Boolean) -> Unit = {},
    error: String?
) {
    var expanded by remember { mutableStateOf(false) }

    val icon = Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onFocusChanged(!expanded)
            expanded = !expanded
        }
    ) {
        FitnestTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            leadingIcon = {
                Image(
                    painter = painterResource(image),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    stringResource(title),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = { Icon(icon, null, modifier = Modifier.rotate(if (expanded) 180F else 0F)) },
            onValueChange = {},
            error = error,
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
            values.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemClicked(it)
                        onFocusChanged(false)
                        expanded = false
                    },
                    text = {
                        Text(
                            stringResource(it),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                )
            }
        }
    }
}
