package com.fitnest.presentation.decompose.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.fitnest.presentation.MR
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.view.ButtonWithProgress
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OnboardingAreaView(component: OnboardingAreaComponent) {
    Children(component.childStack, animation = stackAnimation(slide())) {
        val pageComponent = it.instance.component
        val model = pageComponent.model.value

        Scaffold(
            floatingActionButton = {
                ButtonWithProgress(
                    size = Dimen.Dimen50,
                    previousProgress = model.progress - 0.25F,
                    progress = model.progress,
                    onClick = pageComponent::submitStep
                ) {
                    Image(
                        painter = painterResource(MR.images.ic_onboarding_arrow_right),
                        contentDescription = null
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Column(modifier = Modifier.padding(it)) {
                Image(
                    painter = painterResource(model.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(model.title),
                    modifier = Modifier.padding(top = Padding.Padding30, start = Padding.Padding30),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = stringResource(model.description),
                    modifier = Modifier.padding(
                        top = Padding.Padding15,
                        start = Padding.Padding30,
                        end = Padding.Padding30
                    ),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}
