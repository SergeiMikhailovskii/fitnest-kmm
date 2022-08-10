package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.style.BrandGradient
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsSemiBoldStyle14Black

@Preview
@Composable
internal fun TodayTargetBlock() {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(BrandGradient),
                alpha = 0.2F,
                shape = RoundedCornerShape(Dimen.Dimen22)
            )
    ) {
        Row {
            Text(
                "Today Target",
                modifier = Modifier.padding(top = Padding.Padding20, start = Padding.Padding20),
                style = PoppinsSemiBoldStyle14Black
            )
            Box(modifier = Modifier.weight(1F))
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .padding(top = Padding.Padding20, end = Padding.Padding20),
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(BrandGradient)
                        )
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        }
        Row {
            TargetViewBlock()
            TargetViewBlock()
        }
    }
}

@Preview
@Composable
private fun TargetViewBlock() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Icon")
        Column {
            Text("Text1")
            Text("Text2")
        }
    }
}