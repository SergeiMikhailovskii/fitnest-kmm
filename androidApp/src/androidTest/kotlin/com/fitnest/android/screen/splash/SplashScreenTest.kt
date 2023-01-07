package com.fitnest.android.screen.splash

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.fitnest.android.R
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun splashScreen() {
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
}
