package com.fitnest.android.screen.registration.complete_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.BorderColor
import com.fitnest.android.style.Padding.Padding10
import com.fitnest.android.style.Padding.Padding14
import com.fitnest.android.style.Padding.Padding15
import com.fitnest.android.style.Padding.Padding30
import com.fitnest.android.style.PoppinsBoldStyle20Black
import com.fitnest.android.style.PoppinsNormalStyle12Gray1
import com.fitnest.android.style.PoppinsNormalStyle12Gray2

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun CompleteAccountRegistrationScreenPreview(
) {
    CompleteAccountRegistrationScreen(NavController(LocalContext.current))
}

@Composable
fun CompleteAccountRegistrationScreen(
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }

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
        val sexList = listOf("Male", "Female")

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
        Box(
            modifier = Modifier
                .constrainAs(sexDropdown) {
                    top.linkTo(textStepDescription.bottom, Padding30)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(start = Padding30, end = Padding30)
                .clip(RoundedCornerShape(Padding14))
                .background(color = BorderColor)
        ) {
            Row {
                Text(
                    "Choose Gender",
                    style = PoppinsNormalStyle12Gray2,
                    modifier = Modifier
                        .clickable(
                            onClick = { expanded = true }
                        )
                        .fillMaxWidth()
                        .padding(Padding15)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                sexList.forEach {
                    DropdownMenuItem(onClick = { expanded = false }) {
                        Text(it)
                    }
                }
            }
        }
    }

}