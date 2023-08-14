package com.fitnest.presentation.screen.privateArea.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SettingsItem(
    icon: String,
    title: StringResource,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(Dimen.Dimen20)
        )
        Text(
            stringResource(title),
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = Padding.Padding10),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        trailing?.invoke() ?: Image(
            painter = painterResource("ic_chevron_right.xml"),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}
