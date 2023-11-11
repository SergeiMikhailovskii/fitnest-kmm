package com.fitnest.presentation.decompose.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.view.ButtonWithProgress
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingAreaView(component: OnboardingAreaComponent) {
    Children(component.childStack) {
        val component = it.instance.component
        val model = component.model.value

        Scaffold(
            floatingActionButton = {
                ButtonWithProgress(
                    size = Dimen.Dimen50,
                    previousProgress = model.progress - 0.25F,
                    progress = model.progress,
                    onClick = component::submitStep
                ) {
                    Image(
                        painter = painterResource("ic_onboarding_arrow_right.xml"),
                        contentDescription = null
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
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
