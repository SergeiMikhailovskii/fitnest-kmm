package com.fitnest.presentation.screen.privateArea.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.PlaceholderHighlightMultiplatform
import com.fitnest.presentation.extension.brandGradient
import com.fitnest.presentation.extension.fade
import com.fitnest.presentation.extension.placeholder
import com.fitnest.presentation.screen.privateArea.home.HomeViewModel
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun TodayTargetBlock(
    viewModel: HomeViewModel,
    progress: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Padding.Padding30)
            .background(
                brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient),
                shape = RoundedCornerShape(Dimen.Dimen16),
                alpha = 0.2F
            )
            .placeholder(
                progress,
                highlight = PlaceholderHighlightMultiplatform.fade(),
                shape = RoundedCornerShape(Dimen.Dimen16)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = Padding.Padding20,
                vertical = Padding.Padding15
            )
        ) {
            Text(
                stringResource(MR.strings.private_area_dashboard_today_target_title),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = viewModel::navigateToActivityTracker,
                shape = CircleShape,
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
            ) {
                Box(
                    modifier = Modifier.background(
                        brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient)
                    )
                ) {
                    Text(
                        stringResource(MR.strings.private_area_dashboard_today_target_check),
                        modifier = Modifier.padding(
                            horizontal = Padding.Padding20,
                            vertical = Padding.Padding10
                        )
                    )
                }
            }
        }
    }
}
