package com.fitnest.domain.entity.response

import com.fitnest.domain.enum.GoalType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfilePageResponse(
    val widgets: ProfileWidgets? = null
) {

    @Serializable
    class ProfileWidgets(
        @SerialName("PROFILE_INFO_WIDGET")
        val profileInfoWidget: ProfileInfoWidget? = null
    )

    @Serializable
    class ProfileInfoWidget(
        @SerialName("first_name")
        val firstName: String? = null,
        @SerialName("last_name")
        val lastName: String? = null,
        val program: GoalType? = null,
        val height: Int? = null,
        val weight: Int? = null,
        val age: Int? = null
    )
}
