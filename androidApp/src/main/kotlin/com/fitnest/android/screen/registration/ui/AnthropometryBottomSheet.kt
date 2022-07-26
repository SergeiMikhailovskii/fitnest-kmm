package com.fitnest.android.screen.registration.ui

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.fitnest.android.R
import com.fitnest.android.style.BrandColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AnthropometryBottomSheet(
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    minValue: Int,
    maxValue: Int,
    initialValue: Int = 0,
    onSubmit: (Int) -> Unit,
) {
    val context = LocalContext.current

    Column(modifier = Modifier.background(color = Color.White)) {
        var picker: NumberPicker? = null
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Text(
                context.getString(R.string.registration_complete_account_cancel),
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                        picker = null
                    }
                })
            Box(modifier = Modifier.weight(1F))
            Text(context.getString(R.string.registration_complete_account_save),
                color = BrandColor,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        onSubmit(picker?.value ?: 0)
                        modalBottomSheetState.hide()
                        picker = null
                    }
                })
        }
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                picker = NumberPicker(context).apply {
                    this.minValue = minValue
                    this.maxValue = maxValue
                    this.value = initialValue
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                }
                picker!!
            }
        )
    }
}