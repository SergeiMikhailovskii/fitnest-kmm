package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.entity.response.DashboardResponse
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ActivityTrackerResponseToCacheMapperTest {

    private val mapper by lazy<ActivityTrackerResponseToCacheMapperAlias> {
        ActivityTrackerResponseToCacheMapper(Json)
    }

    @Test
    fun map() {
        val input = ActivityTrackerPageResponse(
            widgets = ActivityTrackerPageResponse.ActivityTrackerWidgets(
                activityProgressWidget = ActivityTrackerPageResponse.ActivityProgressWidget(
                    progresses = listOf(
                        ActivityTrackerPageResponse.Progress(
                            date = LocalDate(2023, 2, 26),
                            total = 1000
                        )
                    )
                ),
                latestActivityWidget = ActivityTrackerPageResponse.LatestActivityWidget(
                    activities = listOf(
                        ActivityTrackerPageResponse.Activity(
                            id = 1,
                            amount = 1000,
                            time = LocalDateTime(2023, 2, 26, 0, 0)
                        )
                    )
                ),
                todayTargetWidget = DashboardResponse.TodayTargetWidget(
                    waterIntake = 1000,
                    steps = 1000
                )
            )
        )
        val output = mapper.map(input)
        assertEquals(
            "{\"progresses\":[{\"date\":\"2023-02-26\",\"total\":1000}]}",
            output.activityProgressWidget
        )
        assertEquals(
            "{\"activities\":[{\"id\":1,\"amount\":1000,\"time\":\"2023-02-26T00:00\"}]}",
            output.latestActivityWidget
        )
        assertEquals("{\"water_intake\":1000,\"steps\":1000}", output.todayTargetWidget)
    }
}
