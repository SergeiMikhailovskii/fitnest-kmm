package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.ProfileCacheModel
import com.fitnest.domain.entity.response.ProfilePageResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProfileResponseToCacheMapper(
    private val json: Json
) : Mapper<ProfilePageResponse, ProfileCacheModel> {
    override fun map(source: ProfilePageResponse?): ProfileCacheModel {
        val profileInfoWidgetStr = source?.widgets?.profileInfoWidget?.let(json::encodeToString)
        return ProfileCacheModel(
            profileInfoWidget = profileInfoWidgetStr,
            timeAt = Clock.System.now().toEpochMilliseconds()
        )
    }
}
