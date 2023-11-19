package com.fitnest.presentation.decompose.dialog.date

import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.internal.date.setTimeInMs

class DefaultDateDialogComponent(
    private val onDateSelected: (Date) -> Unit,
    private val onDismiss: () -> Unit
) : DateDialogComponent {

    override fun setDateMillis(millis: Long) = onDateSelected(Date().setTimeInMs(millis))

    override fun onDismiss() = onDismiss.invoke()
}
