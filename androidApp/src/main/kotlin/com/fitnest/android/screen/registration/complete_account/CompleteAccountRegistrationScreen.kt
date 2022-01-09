package com.fitnest.android.screen.registration.complete_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.fitnest.android.style.Padding.Padding10
import com.fitnest.android.style.Padding.Padding30
import org.kodein.di.compose.rememberInstance

@ExperimentalMaterialApi
@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun CompleteAccountRegistrationScreenPreview(
) {
    CompleteAccountRegistrationScreen(NavController(LocalContext.current))
}

@ExperimentalMaterialApi
@Composable
fun CompleteAccountRegistrationScreen(
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        val (imageTop, textStepTitle, textStepDescription, sexDropdown) = createRefs()

        Image(
            painter = painterResource(
                id = R.drawable.ic_registration_complete_account
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(imageTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        )
        Text(
            "Let’s complete your profile",
            style = PoppinsBoldStyle20Black,
            modifier = Modifier.constrainAs(textStepTitle) {
                top.linkTo(imageTop.bottom, Padding30)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            "It will help us to know more about you!",
            style = PoppinsNormalStyle12Gray1,
            modifier = Modifier.constrainAs(textStepDescription) {
                top.linkTo(textStepTitle.bottom, Padding10)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        SexDropdown(
            modifier = Modifier
                .padding(start = Padding30, end = Padding30)
                .constrainAs(sexDropdown) {
                    top.linkTo(textStepDescription.bottom, Padding30)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun SexDropdown(modifier: Modifier) {
    val viewMapper by rememberInstance<CompleteAccountRegistrationViewMapper>()

    var expanded by remember { mutableStateOf(false) }
    val sexList by remember { mutableStateOf(listOf("Male", "Female")) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        modifier = modifier
            .clickable(onClick = { expanded = true }),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BorderColor,
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
                    "Choose Gender",
                    style = PoppinsNormalStyle14
                )
            },
            readOnly = true
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            sexList.forEach {
                DropdownMenuItem(
                    onClick = {
                        val sex = viewMapper.mapSexStringToEnumField(it)
                        expanded = false
                    },
                ) {
                    Text(it, style = PoppinsNormalStyle12Gray2)
                }
            }
        }
    }
}