package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
private fun ProfileUserInfoCardPreview() {
    ProfileUserInfoCard(Modifier)
}

@Composable
internal fun ProfileUserInfoCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Dimen20),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Padding24, vertical = Padding.Padding10)
        ) {
            Text(
                "180cm",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                "Height",
                modifier = Modifier.padding(top = Padding.Padding5),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}