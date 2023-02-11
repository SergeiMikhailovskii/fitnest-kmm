package com.fitnest.android.screen.private_area.activity_tracker.input

import android.widget.NumberPicker
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.R
import com.fitnest.android.base.Route
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.extension.enum.fromLocalizedName
import com.fitnest.android.extension.enum.localizedNames
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.domain.enum.ActivityType
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Preview
@Composable
internal fun ActivityInputBottomSheetPreview() {
//    ActivityInputBottomSheet()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ActivityInputBottomSheet(
    sheetState: ModalBottomSheetState,
    navigate: (Route) -> Unit
) = subDI(diBuilder = { import(PrivateAreaModule.activityInputPrivateAreaModule) }) {

    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel<ActivityInputViewModel>(factory = viewModelFactory)

    val screenData by viewModel.screenDataFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = null) {
        launch { sheetState.animateTo(ModalBottomSheetValue.Expanded) }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        launch { viewModel.routeSharedFlow.collect { navigate(it) } }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = Padding.Padding8)
                .width(Dimen.Dimen60)
                .height(Dimen.Dimen8)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Row(
            modifier = Modifier
                .padding(top = Padding.Padding24)
                .fillMaxWidth()
                .padding(horizontal = Padding.Padding30)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.error),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActivityType.localizedNames(context).forEach {
                val background by animateColorAsState(
                    targetValue =
                    if (screenData.activityType == ActivityType.fromLocalizedName(it, context))
                        MaterialTheme.colorScheme.primary
                    else Color.Transparent,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
                Text(
                    it,
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1F)
                        .background(background)
                        .clickable {
                            viewModel.setCurrentActiveTab(
                                ActivityType.fromLocalizedName(it, context)
                            )
                        }
                        .padding(
                            vertical = Padding.Padding8,
                            horizontal = Padding.Padding16
                        ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
        AndroidView(
            modifier = Modifier
                .padding(top = Padding.Padding20)
                .padding(horizontal = Padding.Padding30)
                .fillMaxWidth(),
            factory = {
                NumberPicker(it).apply {
                    this.minValue = 0
                    this.maxValue = 10
                    this.value = 0
                    setFormatter { value ->
                        value.times(100).toString()
                    }
                    setOnValueChangedListener { _, _, newVal ->
                        viewModel.setValue(newVal * 100)
                    }
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = true
                }
            }
        )
        Button(
            onClick = viewModel::submitActivity,
            shape = CircleShape,
            modifier = Modifier
                .padding(all = Padding.Padding30)
                .height(Dimen.Dimen60)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.private_area_activity_tracker_screen_latest_activity_save),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Box(modifier = Modifier.height(20.dp))
    }
}
