package com.fitnest.android.screen.registration.goal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*

@Composable
fun GoalRegistrationScreen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            textStepTitle,
            textStepDescription,
            btnNext,
        ) = createRefs()

        Text(
            "What is your goal?",
            modifier = Modifier
                .constrainAs(textStepTitle) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = Padding.Padding40)
                },
            style = PoppinsBoldStyle20Black
        )
        Text(
            "It will help us to choose a best program for you",
            modifier = Modifier
                .constrainAs(textStepDescription) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(textStepTitle.bottom, margin = Padding.Padding5)
                },
            style = PoppinsNormalStyle12Gray1
        )
        Button(
            onClick = {},
            shape = CircleShape,
            modifier = Modifier
                .constrainAs(btnNext) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding40
                )
                .height(Dimen.Dimen60)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.registration_complete_account_next_button_label),
                style = PoppinsBoldStyle16
            )
            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
        }
    }
}