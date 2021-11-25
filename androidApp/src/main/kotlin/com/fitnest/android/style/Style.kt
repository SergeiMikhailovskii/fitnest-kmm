package com.fitnest.android.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val PoppinsStyle = TextStyle(
    fontFamily = poppinsFamily
)

val PoppinsMediumStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Medium
)

val PoppinsMediumStyle14 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size14
)

val PoppinsMediumStyle14Gray1 = PoppinsMediumStyle14.copy(
    color = GrayColor1
)

val PoppinsBoldStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Bold
)

val PoppinsBoldStyle20 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size20
)

val PoppinsBoldStyle20Black = PoppinsBoldStyle20.copy(
    color = BlackColor
)

val PoppinsBoldStyle24 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size24
)

val PoppinsBoldStyle24Black = PoppinsBoldStyle24.copy(
    color = BlackColor
)

val PoppinsNormalStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Normal
)

val PoppinsNormalStyle16 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size16
)

val PoppinsNormalStyle16Black = PoppinsNormalStyle16.copy(
    color = BlackColor
)