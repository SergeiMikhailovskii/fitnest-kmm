package com.mikhailovskii.kmmtest.android.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
/**
 * This import should not be deleted because app will crash without it
 */
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.entity.OnboardingState
import org.kodein.di.compose.instance
import org.kodein.di.compose.withDI

@Composable
fun OnboardingScreen(navController: NavController, type: String) = withDI {
    val viewModel: OnboardingViewModel by instance()
    val screenState: OnboardingState? by viewModel.stateLiveData.observeAsState(null)

    LaunchedEffect(key1 = null) {
        viewModel.updateScreenState(type)
    }

    Scaffold(floatingActionButton = {
        Button(onClick = {}, modifier = Modifier.size(50.dp),shape = CircleShape) {
            Text("A")
        }
    }, floatingActionButtonPosition = FabPosition.End) {
        Column {
            Image(
                painter = painterResource(
                    id = screenState?.imageResId ?: R.drawable.ic_onboarding_first
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Track Your Goal", modifier = Modifier.padding(top = 64.dp, start = 30.dp))
            Text(
                text = "Don't worry if you have trouble determining your goals, We can help you determine your goals and track your goals",
                modifier = Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp)
            )
        }
    }
}