package com.fitnest.android.screen.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.fitnest.android.R
import com.fitnest.android.extension.enum.localizedNames
import com.fitnest.android.view.ui_elements.FitnestTextField
import com.fitnest.domain.enum.SexType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SexDropdown(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    value: String,
    onFocusChanged: (Boolean) -> Unit,
    error: String?
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
        FitnestTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_complete_registration_sex),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    context.getString(R.string.registration_complete_account_choose_gender),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = { Icon(icon, null) },
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
            sexList.forEach {
                DropdownMenuItem(
                    onClick = {
                        onItemClicked(it)
                        onFocusChanged(false)
                        expanded = false
                    },
                    text = {
                        Text(
                            it,
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
