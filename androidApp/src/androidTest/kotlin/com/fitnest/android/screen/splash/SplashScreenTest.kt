package com.fitnest.android.screen.splash

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import com.fitnest.android.R
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.base.Route
import com.fitnest.android.di.splashModule
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.kodein.di.DI
import org.kodein.di.bindProvider
import kotlin.time.ExperimentalTime

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalTime::class, ExperimentalPagerApi::class,
    ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class, ExperimentalFoundationApi::class
)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @MockK(relaxed = true)
    internal lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun splashScreenLoadedSuccess() {
        val context = composeTestRule.activity
        val progressFlow = MutableStateFlow(false)
        val routeFlow = MutableSharedFlow<Route>()
        val failureFlow = MutableSharedFlow<Failure>()

        val navController = TestNavHostController(context)
        navController.navigatorProvider.addNavigator(AnimatedComposeNavigator())

        every { viewModel.progressStateFlow } returns progressFlow
        every { viewModel.routeSharedFlow } returns routeFlow
        every { viewModel.failureSharedFlow } returns failureFlow
        every { viewModel.generateToken() } coAnswers {
            progressFlow.emit(true)
            progressFlow.emit(false)
        }
        mockkStatic(::splashModule)
        every { splashModule } returns DI.Module(name = "mock splash module") {
            bindProvider { viewModel }
        }
        composeTestRule.setContent {
            FitnestApp(navController = navController)
        }
        composeTestRule.waitUntilExists(
            matcher = hasText(context.getString(R.string.splash_button_title)),
            timeoutMillis = 60_000
        )
    }

    @Test
    fun splashScreenLoadedSuccessAndRedirectNext() {
        val context = composeTestRule.activity
        val progressFlow = MutableStateFlow(false)
        val routeFlow = MutableSharedFlow<Route>()
        val failureFlow = MutableSharedFlow<Failure>()

        val navController = TestNavHostController(context)
        navController.navigatorProvider.addNavigator(AnimatedComposeNavigator())

        every { viewModel.progressStateFlow } returns progressFlow
        every { viewModel.routeSharedFlow } returns routeFlow
        every { viewModel.failureSharedFlow } returns failureFlow
        every { viewModel.generateToken() } coAnswers {
            progressFlow.emit(true)
            progressFlow.emit(false)
        }
        every { viewModel.navigateNext() } coAnswers {
            routeFlow.emit(Route.Proxy())
        }
        mockkStatic(::splashModule)
        every { splashModule } returns DI.Module(name = "mock splash module") {
            bindProvider { viewModel }
        }
        composeTestRule.setContent {
            FitnestApp(navController = navController)
        }
        composeTestRule.waitUntilExists(
            matcher = hasText(context.getString(R.string.splash_button_title)),
            timeoutMillis = 60_000
        )
        composeTestRule.onNodeWithTag("splash_btn_next").performClick()
        composeTestRule.waitForIdle()
        assertEquals(
            "proxy/{flowType}",
            navController.currentBackStackEntry?.destination?.route
        )
        assertTrue(navController.currentBackStackEntry?.arguments?.containsKey("flowType") == true)

        val flow = navController.currentBackStackEntry?.arguments?.getSerializable("flowType")
        assertEquals(FlowType.UNKNOWN, flow)
    }

    @Test
    fun splashScreenLoadedFailure() {
        val context = composeTestRule.activity
        val mockGenerateToken = mockk<GenerateTokenUseCase>()
        mockkStatic(::splashModule)
        coEvery { mockGenerateToken() } returns Result.failure(Failure.ServerError(500))
        every { splashModule } returns DI.Module(name = "mock splash module") {
            bindProvider { SplashViewModel(generateTokenUseCase = mockGenerateToken) }
        }
        composeTestRule.setContent { FitnestApp() }
        composeTestRule.onNodeWithTag("snackbarError").assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.splash_button_title))
            .assertIsDisplayed()
    }
}
