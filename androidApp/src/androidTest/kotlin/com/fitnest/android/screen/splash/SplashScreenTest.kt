package com.fitnest.android.screen.splash

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.base.FitnestApp
import com.fitnest.android.di.splashModule
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.google.accompanist.pager.ExperimentalPagerApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Rule
import org.junit.Test
import org.kodein.di.DI
import org.kodein.di.bindProvider
import kotlin.time.ExperimentalTime

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun splashScreenLoadedSuccess() {
        val context = composeTestRule.activity
        composeTestRule.setContent {
            SplashScreen(navController = NavController(LocalContext.current))
        }
        composeTestRule.waitUntilExists(
            matcher = hasText(context.getString(R.string.splash_button_title)),
            timeoutMillis = 60_000
        )
        composeTestRule.onNodeWithText(
            context.getString(R.string.splash_button_title),
            ignoreCase = true
        ).assertIsDisplayed()
    }

    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalTime::class, ExperimentalPagerApi::class,
        ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
        ExperimentalAnimationApi::class, ExperimentalFoundationApi::class
    )
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
