package com.fitnest.android.screen.registration.goal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun GoalRegistrationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "What is your goal?",
            modifier = Modifier.padding(top = Padding.Padding40),
            style = PoppinsBoldStyle20Black
        )
        Text(
            "It will help us to choose a best program for you",
            modifier = Modifier.padding(top = Padding.Padding5),
            style = PoppinsNormalStyle12Gray1
        )
        HorizontalPager(
            count = 3,
            modifier = Modifier
                .padding(top = Padding.Padding50, bottom = Padding.Padding70)
                .weight(1F)
        ) { page ->
            val image = when (page) {
                0 -> {
                    R.drawable.ic_registration_goal_improve_shape
                }
                1 -> {
                    R.drawable.ic_registration_goal_lean_tone
                }
                else -> {
                    R.drawable.ic_registration_goal_lose_fat
                }
            }
            Image(
                painter = painterResource(id = image),
                contentDescription = null
            )
        }
        Button(
            onClick = {},
            shape = CircleShape,
            modifier = Modifier
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