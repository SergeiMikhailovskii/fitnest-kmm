package com.fitnest.android.screen.registration.goal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.lerp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.RegistrationModule
import com.fitnest.android.extension.brandGradient
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.goal.GoalRegistrationViewMapper
import com.fitnest.presentation.screen.registration.goal.GoalRegistrationViewModel
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun GoalRegistrationScreen(navigate: (Route) -> Unit) = subDI(diBuilder = {
    import(RegistrationModule.goalRegistrationScreenModule)
}) {
    val viewMapper: GoalRegistrationViewMapper by rememberInstance()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = GoalRegistrationViewModel::class.java
    )

    val pagerState = rememberPagerState()
    val pageCount = 3

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            val goalType = viewMapper.mapGoalIndexToGoalType(page)
            viewModel.setGoal(goalType)
        }
    }

    com.fitnest.presentation.screen.registration.goal.GoalRegistrationScreen(
        viewModel = viewModel,
        onNavigate = navigate
    ) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            HorizontalPager(
                contentPadding = PaddingValues(horizontal = Padding.Padding50),
                count = pageCount,
                modifier = Modifier
                    .padding(top = Padding.Padding50, bottom = Padding.Padding70)
                    .weight(1F),
                state = pagerState
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
                            .background(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient))
                            .padding(top = Padding.Padding40)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(pagerItemModel.image),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Text(
                            stringResource(pagerItemModel.title),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.SemiBold
                            ),
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = Padding.Padding3)
                                .width(Dimen.Dimen50)
                                .height(Dimen.Dimen1)
                                .background(MaterialTheme.colorScheme.onPrimary)
                        )
                        Text(
                            stringResource(pagerItemModel.description),
                            modifier = Modifier.padding(
                                top = Padding.Padding20,
                                start = Padding.Padding30,
                                end = Padding.Padding30,
                                bottom = Padding.Padding30
                            ),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
