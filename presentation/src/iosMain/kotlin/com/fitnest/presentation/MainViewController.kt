package com.fitnest.presentation

import androidx.compose.ui.window.ComposeUIViewController
import com.fitnest.presentation.decompose.RootComponent

fun MainViewController(component: RootComponent) = ComposeUIViewController { FitnestApp(component) }
