package com.fitnest.android.screen.registration.goal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import org.kodein.di.compose.rememberInstance
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun GoalRegistrationScreen(navController: NavController) {
    val viewMapper: GoalRegistrationViewMapper by rememberInstance()

    val context = LocalContext.current

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
        CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
            HorizontalPager(
                contentPadding = PaddingValues(horizontal = Padding.Padding50),
                count = pageCount,
                modifier = Modifier
                    .padding(top = Padding.Padding50, bottom = Padding.Padding70)
                    .weight(1F)
            ) { index ->
                val pagerItemModel = viewMapper.mapGoalIndexToUIModel(index)

                Card(
                    shape = RoundedCornerShape(Dimen.Dimen22),
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
                            .background(brush = Brush.horizontalGradient(BrandGradient))
                            .padding(top = Padding.Padding40)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = pagerItemModel.image),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Text(
                            context.getString(pagerItemModel.title),
                            style = PoppinsSemiBoldStyle14White,
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = Padding.Padding3)
                                .width(Dimen.Dimen50)
                                .height(Dimen.Dimen1)
                                .background(WhiteColor)
                        )
                        Text(
                            context.getString(pagerItemModel.description),
                            modifier = Modifier.padding(
                                top = Padding.Padding20,
                                start = Padding.Padding30,
                                end = Padding.Padding30,
                                bottom = Padding.Padding30
                            ),
                            style = PoppinsNormalStyle12White,
                            textAlign = TextAlign.Center
                        )
                    }
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
                text = stringResource(id = R.string.registration_goal_next_button_label),
                style = PoppinsBoldStyle16
            )
        }
    }
}