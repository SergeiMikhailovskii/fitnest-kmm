package com.fitnest.android.screen.privateArea.activityTracker.input

import androidx.activity.ComponentActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.base.Route
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.extension.enum.localizedName
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputScreenData
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputViewModel
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.functional.Failure
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.kodein.di.DI
import org.kodein.di.bindProvider

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class)
class ActivityInputBottomSheetTest {

    @get:Rule
    val composeTestRule by lazy { createAndroidComposeRule<ComponentActivity>() }

    @MockK(relaxed = true)
    internal lateinit var viewModel: ActivityInputViewModel

    private val routeFlow by lazy { MutableSharedFlow<Route>() }
    private val failureFlow by lazy { MutableSharedFlow<Failure>() }
    private val screenDataFlow by lazy { MutableStateFlow(ActivityInputScreenData()) }
    private val context by lazy { composeTestRule.activity }

    @Before
    fun setUpMocks() = MockKAnnotations.init(this)

    @Before
    fun setUpFlows() {
        mockkObject(PrivateAreaModule)
        every { viewModel.routeSharedFlow } returns routeFlow
        every { viewModel.failureSharedFlow } returns failureFlow
        every { viewModel.screenDataFlow } returns screenDataFlow
        every { PrivateAreaModule.activityInputPrivateAreaModule } returns
                DI.Module(name = "mock activity input private area module") {
                    bindProvider { viewModel }
                }
    }

    @Test
    fun clickAllSelectorButtons() {
        ActivityType.values().forEach { activity ->
            every { viewModel.setCurrentActiveTab(activity) } coAnswers {
                screenDataFlow.emit(ActivityInputScreenData(activityType = activity))
            }
        }
        composeTestRule.setContent {
            FitnestApp(startDestination = Route.PrivateArea.Tracker.ActivityInputBottomSheet.pattern)
        }
        ActivityType.values().forEach {
            composeTestRule.onNodeWithText(it.localizedName(context)).performClick()
            composeTestRule.waitForIdle()

            assertEquals(it, screenDataFlow.value.activityType)
        }
    }
}