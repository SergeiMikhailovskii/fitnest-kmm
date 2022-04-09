package com.fitnest.android.screen.registration.goal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun GoalRegistrationScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pageCount = 3

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
            contentPadding = PaddingValues(horizontal = 50.dp),
            count = pageCount,
            modifier = Modifier
                .padding(top = Padding.Padding50, bottom = Padding.Padding70)
                .weight(1F)
        ) { index ->
            val image = when (index) {
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
            Card(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null
                    )
                }
            }
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

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}