package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
private fun ProfileInfoBlockPreview() {
    ProfileInfoBlock(modifier = Modifier)
}

@Composable
internal fun ProfileInfoBlock(modifier: Modifier) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_private_area_activity_water),
                contentDescription = null,
                modifier = Modifier.size(Dimen.Dimen55)
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = Padding.Padding15)
            ) {
                Text("Stefani Wong", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Lose a Fat Program", style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
            Button(onClick = { }) {
                Text(text = "Edit", style = MaterialTheme.typography.bodySmall)
            }
        }

        Row(
            modifier = Modifier.padding(
                top = Padding.Padding15,
            )
        ) {
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = "180cm",
                description = "Height"
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = "65kg",
                description = "Weight"
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = "22yo",
                description = "Age"
            )
        }
    }
}