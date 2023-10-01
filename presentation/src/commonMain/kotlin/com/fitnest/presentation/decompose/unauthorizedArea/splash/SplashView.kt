package com.fitnest.presentation.decompose.unauthorizedArea.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.brandGradient
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SplashView() {
    Box(
        modifier = Modifier
            .background(brush = Brush.verticalGradient(colors = MaterialTheme.colorScheme.brandGradient))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(MR.images.ic_splash_logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
        FilledTonalButton(
            onClick = {},
            modifier = Modifier
                .padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding40
                )
                .height(Dimen.Dimen60)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .testTag("splash_btn_next")
        ) {
            Text(
                text = stringResource(MR.strings.splash_button_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
