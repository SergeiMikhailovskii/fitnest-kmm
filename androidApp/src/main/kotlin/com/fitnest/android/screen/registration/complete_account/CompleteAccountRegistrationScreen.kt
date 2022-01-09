package com.fitnest.android.screen.registration.complete_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun SexDropdown(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(Size.Zero) }
    val sexList by remember { mutableStateOf(listOf("Male", "Female")) }
    Box(
        modifier = modifier
            .clickable(onClick = { expanded = true })
            .fillMaxWidth()
            .clip(RoundedCornerShape(Padding14))
            .background(color = BorderColor)
            .onGloballyPositioned { layoutCoordinates ->
                rowSize = layoutCoordinates.size.toSize()
            }
    ) {
        Row(
            modifier = Modifier.padding(Padding15)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_complete_registration_sex),
                contentDescription = null
            )
            Text(
                "Choose Gender",
                style = PoppinsNormalStyle12Gray2,
                modifier = Modifier
                    .padding(start = Padding10)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .width(with(LocalDensity.current) { rowSize.width.toDp() }),
        ) {
            sexList.forEach {
                DropdownMenuItem(
                    onClick = { expanded = false },
                ) {
                    Text(it, style = PoppinsNormalStyle12Gray2)
                }

                if (sexList.last() != it) {
                    Divider()
                }
            }
        }
    }
}