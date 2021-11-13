package com.fitnest.android.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PoppinsMediumStyle = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.Medium
)

val PoppinsMediumStyle14 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size14
)

val PoppinsMediumStyle14Gray1 = PoppinsMediumStyle14.copy(
    color = GrayColor1
)

val PoppinsBoldStyle = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.Bold
)

val PoppinsBoldStyle24 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size24
)

val PoppinsBoldStyle24Black = PoppinsBoldStyle24.copy(
    color = BlackColor
)