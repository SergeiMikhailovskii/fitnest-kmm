package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.ProfileCacheModel
import com.fitnest.domain.entity.response.ProfilePageResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ProfileCacheToResponseMapper(
    private val json: Json
) : Mapper<ProfileCacheModel, ProfilePageResponse> {
    override fun map(source: ProfileCacheModel?): ProfilePageResponse {
        val profileInfoWidget =
            source?.profileInfoWidget?.let<String, ProfilePageResponse.ProfileInfoWidget>(json::decodeFromString)
        return ProfilePageResponse(
            widgets = ProfilePageResponse.ProfileWidgets(profileInfoWidget = profileInfoWidget)
        )
    }
}
