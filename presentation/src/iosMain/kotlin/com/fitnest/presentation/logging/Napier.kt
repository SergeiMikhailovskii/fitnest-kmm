package com.fitnest.presentation.logging

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initNapier() {
    Napier.base(DebugAntilog("Fitnest"))
}
