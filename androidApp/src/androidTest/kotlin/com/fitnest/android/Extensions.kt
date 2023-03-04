package com.fitnest.android

import android.view.View
import android.widget.NumberPicker
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import java.util.Timer
import java.util.TimerTask
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = 1_000L
) {
    this.waitUntil(timeoutMillis) {
        this.onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}

fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    return this.waitUntilNodeCount(matcher, 1, timeoutMillis)
}

fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    return this.waitUntilNodeCount(matcher, 0, timeoutMillis)
}

fun ComposeContentTestRule.waitUntilTimeout(
    timeoutMillis: Long
) {
    AsyncTimer.start(timeoutMillis)
    this.waitUntil(
        condition = { AsyncTimer.expired },
        timeoutMillis = timeoutMillis + 1000
    )
}

fun scrollDown() = object : ViewAction {
    override fun getDescription() = ""

    override fun getConstraints() = ViewMatchers.isAssignableFrom(NumberPicker::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        val method = NumberPicker::class.declaredFunctions
            .firstOrNull { it.name == "changeValueByOne" }
            ?.apply {
                isAccessible = true
            }
        method?.call(view, true)
    }
}

fun scrollUp() = object : ViewAction {
    override fun getDescription() = ""

    override fun getConstraints() = ViewMatchers.isAssignableFrom(NumberPicker::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        val method = NumberPicker::class.declaredFunctions
            .firstOrNull { it.name == "changeValueByOne" }
            ?.apply {
                isAccessible = true
            }
        method?.call(view, false)
    }
}

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000) {
        expired = false
        Timer().schedule(object : TimerTask() {
            override fun run() {
                expired = true
            }
        }, delay)
    }
}