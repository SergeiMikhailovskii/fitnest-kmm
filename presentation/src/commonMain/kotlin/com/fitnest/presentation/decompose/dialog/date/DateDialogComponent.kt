package com.fitnest.presentation.decompose.dialog.date

import com.fitnest.presentation.decompose.dialog.DialogComponent

interface DateDialogComponent : DialogComponent {
    fun setDateMillis(millis: Long)
}
