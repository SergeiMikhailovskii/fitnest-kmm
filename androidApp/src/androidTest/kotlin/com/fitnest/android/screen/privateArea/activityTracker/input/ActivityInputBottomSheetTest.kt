package com.fitnest.android.screen.privateArea.activityTracker.input

import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import com.fitnest.android.R
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.base.Route
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.extension.enum.localizedName
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputScreenData
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputViewModel
import com.fitnest.android.scrollDown
import com.fitnest.android.scrollUp
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.functional.Failure
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import io.mockk.slot
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.Matchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.kodein.di.DI
import org.kodein.di.bindProvider

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
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
        val activityType = slot<ActivityType>()
        every { viewModel.setCurrentActiveTab(capture(activityType)) } coAnswers {
            screenDataFlow.emit(ActivityInputScreenData(activityType = activityType.captured))
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

    @Test
    fun scrollPicker() {
        val value = slot<Int>()
        every { viewModel.setValue(capture(value)) } coAnswers {
            screenDataFlow.emit(ActivityInputScreenData(value = value.captured))
        }
        composeTestRule.setContent {
            FitnestApp(startDestination = Route.PrivateArea.Tracker.ActivityInputBottomSheet.pattern)
        }
        val picker = Espresso.onView(withClassName(Matchers.equalTo(NumberPicker::class.java.name)))
        picker.perform(scrollDown())
        composeTestRule.waitForIdle()
        assertEquals(100, screenDataFlow.value.value)
        picker.perform(scrollUp())
        composeTestRule.waitForIdle()
        assertEquals(0, screenDataFlow.value.value)
    }

    @Test
    fun submitData() {
        val bottomSheetNavigator =
            BottomSheetNavigator(sheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden))
        val navController = TestNavHostController(context)
        navController.navigatorProvider.addNavigator(bottomSheetNavigator)
        navController.navigatorProvider.addNavigator(AnimatedComposeNavigator())
        every { viewModel.submitActivity() } coAnswers {
            routeFlow.emit(Route.DismissBottomSheet)
        }
        composeTestRule.setContent {
            FitnestApp(
                startDestination = Route.PrivateArea.Tracker.ActivityInputBottomSheet.pattern,
                bottomSheetNavigator = bottomSheetNavigator,
                navController = navController,
            )
        }
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.private_area_activity_tracker_screen_latest_activity_save),
                ignoreCase = true
            )
            .performClick()
        composeTestRule.waitForIdle()
        assertTrue(navController.currentBackStackEntry == null)
    }
}
