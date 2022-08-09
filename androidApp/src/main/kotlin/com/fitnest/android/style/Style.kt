package com.fitnest.android.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val PoppinsStyle = TextStyle(
    fontFamily = poppinsFamily
)

val PoppinsMediumStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Medium
)

val PoppinsMediumStyle8 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size8
)

val PoppinsMediumStyle8White = PoppinsMediumStyle8.copy(
    color = WhiteColor
)

val PoppinsMediumStyle10 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size10
)

val PoppinsMediumStyle10Gray1 = PoppinsMediumStyle10.copy(
    color = GrayColor1
)

val PoppinsMediumStyle12 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size12
)

val PoppinsMediumStyle12Black = PoppinsMediumStyle12.copy(
    color = BlackColor
)

val PoppinsMediumStyle12Gray2 = PoppinsMediumStyle12.copy(
    color = GrayColor2
)

val PoppinsMediumStyle14 = PoppinsMediumStyle.copy(
    fontSize = TextSize.Size14
)

val PoppinsMediumStyle14White = PoppinsMediumStyle14.copy(
    color = WhiteColor
)

val PoppinsMediumStyle14Gray1 = PoppinsMediumStyle14.copy(
    color = GrayColor1
)

val PoppinsMediumStyle14Black = PoppinsMediumStyle14.copy(
    color = BlackColor
)

val PoppinsBoldStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Bold
)

val PoppinsSemiBoldStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.SemiBold
)

val PoppinsBoldStyle20 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size20
)

val PoppinsBoldStyle20Black = PoppinsBoldStyle20.copy(
    color = BlackColor
)

val PoppinsBoldStyle16 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size16
)

val PoppinsBoldStyle16Brand = PoppinsBoldStyle.copy(
    color = BrandColor
)

val PoppinsBoldStyle16Black = PoppinsBoldStyle16.copy(
    color = BlackColor
)

val PoppinsBoldStyle24 = PoppinsBoldStyle.copy(
    fontSize = TextSize.Size24
)

val PoppinsBoldStyle24Black = PoppinsBoldStyle24.copy(
    color = BlackColor
)

val PoppinsSemiBoldStyle14 = PoppinsSemiBoldStyle.copy(
    fontSize = TextSize.Size14
)

val PoppinsSemiBoldStyle14White = PoppinsSemiBoldStyle14.copy(
    color = WhiteColor
)

val PoppinsSemiBoldStyle16 = PoppinsSemiBoldStyle.copy(
    fontSize = TextSize.Size16
)

val PoppinsSemiBoldStyle16Black = PoppinsSemiBoldStyle16.copy(
    color = BlackColor
)

val PoppinsNormalStyle = PoppinsStyle.copy(
    fontWeight = FontWeight.Normal
)

val PoppinsNormalStyle8 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size8
)

val PoppinsNormalStyle8Gray2 = PoppinsNormalStyle8.copy(
    color = GrayColor2
)

val PoppinsNormalStyle10 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size10
)

val PoppinsNormalStyle10Gray2 = PoppinsNormalStyle10.copy(
    color = GrayColor2
)

val PoppinsNormalStyle12 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size12
)

val PoppinsNormalStyle12Black = PoppinsNormalStyle12.copy(
    color = BlackColor
)

val PoppinsNormalStyle12Gray1 = PoppinsNormalStyle12.copy(
    color = GrayColor1
)

val PoppinsNormalStyle12Gray2 = PoppinsNormalStyle12.copy(
    color = GrayColor2
)

val PoppinsNormalStyle12White = PoppinsNormalStyle12.copy(
    color = WhiteColor
)

val PoppinsNormalStyle14 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size14
)

val PoppinsNormalStyle14Gray2 = PoppinsNormalStyle14.copy(
    color = GrayColor2
)

val PoppinsNormalStyle14Black = PoppinsNormalStyle14.copy(
    color = BlackColor
)

val PoppinsNormalStyle16 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size16
)

val PoppinsNormalStyle16Black = PoppinsNormalStyle16.copy(
    color = BlackColor
)

val PoppinsNormalStyle18 = PoppinsNormalStyle.copy(
    fontSize = TextSize.Size16
)

val PoppinsNormalStyle18Gray1 = PoppinsNormalStyle18.copy(
    color = GrayColor1
)

val ErrorStyle = PoppinsNormalStyle12.copy(
    color = ErrorColor
)