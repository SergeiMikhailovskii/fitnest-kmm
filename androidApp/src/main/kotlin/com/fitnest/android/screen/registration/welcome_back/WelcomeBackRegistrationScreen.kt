package com.fitnest.android.screen.registration.welcome_back

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.style.Padding

@Composable
fun WelcomeBackRegistrationScreen(navController: NavController) {
    Column {
        Image(
            painter = painterResource(
                id = R.drawable.ic_registration_welcome_back
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Padding.Padding50,
                    end = Padding.Padding50,
                    top = Padding.Padding100
                )
        )
    }
}