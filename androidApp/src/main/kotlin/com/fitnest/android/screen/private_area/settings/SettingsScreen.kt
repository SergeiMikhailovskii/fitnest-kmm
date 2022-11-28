package com.fitnest.android.screen.private_area.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.screen.private_area.settings.composables.AccountSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.NotificationSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.OtherSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.ProfileInfoBlock
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
fun SettingsScreen() {
    Column {
        ProfileInfoBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        AccountSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        NotificationSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        OtherSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        Box(modifier = Modifier.height(Dimen.Dimen20))
    }
}