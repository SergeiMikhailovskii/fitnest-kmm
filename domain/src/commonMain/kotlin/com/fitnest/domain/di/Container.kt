package com.fitnest.domain.di

import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerCacheToResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapper
import com.fitnest.domain.mapper.db.ProfileCacheToResponseMapper
import com.fitnest.domain.mapper.db.ProfileResponseToCacheMapper
import com.fitnest.domain.usecase.private_area.AddActivityUseCase
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import com.fitnest.domain.usecase.private_area.DeactivateNotificationsUseCase
import com.fitnest.domain.usecase.private_area.DeleteActivityUseCase
import com.fitnest.domain.usecase.private_area.DeleteNotificationUseCase
import com.fitnest.domain.usecase.private_area.GetActivityTrackerPageUseCase
import com.fitnest.domain.usecase.private_area.GetNotificationsPageUseCase
import com.fitnest.domain.usecase.private_area.GetProfilePageUseCase
import com.fitnest.domain.usecase.private_area.PinNotificationUseCase
import com.fitnest.domain.usecase.private_area.SetNotificationsEnabledUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val useCaseModule = DI.Module("use case module") {
    /**
     * Private area
     */
    bindProvider { GetNotificationsPageUseCase(instance(), instance(), instance()) }
    bindProvider { DeactivateNotificationsUseCase(instance(), instance()) }
    bindProvider { PinNotificationUseCase(instance(), instance()) }
    bindProvider { DeleteNotificationUseCase(instance(), instance()) }
    bindProvider {
        GetActivityTrackerPageUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider {
        DeleteActivityUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider { AddActivityUseCase(instance(), instance(), instance(), instance(), instance()) }
    bindProvider {
        GetProfilePageUseCase(
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bindProvider { ClearCacheUseCase(instance()) }
    bindProvider { SetNotificationsEnabledUseCase(instance()) }
}

val mapperModule = DI.Module("Mapper module") {
    bindProvider { RegistrationResponseMapper(instance()) }
    bindProvider { ActivityTrackerResponseToCacheMapper(instance()) }
    bindProvider { ActivityTrackerCacheToResponseMapper(instance()) }
    bindProvider { ProfileResponseToCacheMapper(instance()) }
    bindProvider { ProfileCacheToResponseMapper(instance()) }
}
