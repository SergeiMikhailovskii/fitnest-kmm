package com.fitnest.repository

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.functional.flatMap
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.service.NetworkService
import com.fitnest.mapper.RegistrationResponseMapper
import com.fitnest.network.Endpoints
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class NetworkRepository(
    private val networkService: NetworkService,
    private val registrationResponseMapper: RegistrationResponseMapper
) : NetworkRepository {

    override suspend fun loginUser(data: LoginData) {
    }

    override suspend fun generateToken() = networkService.getData(Endpoints.Flow.name)

    override suspend fun getOnboardingStep() = networkService.getData(Endpoints.Onboarding.name)
        .map {
            it?.data?.jsonObject?.get("step")?.jsonPrimitive?.content.orEmpty()
        }

    override suspend fun getRegistrationStepData() =
        networkService.getData(Endpoints.Registration.name).map {
            val data = it?.data?.jsonObject
            registrationResponseMapper.map(data)
        }

    override suspend fun submitRegistrationStep(request: BaseRequest): Either<Failure, Unit> =
        networkService.sendData(Endpoints.Registration.name, data = request)
            .flatMap { Either.Right(Unit) }

    override suspend fun submitOnboardingStep() =
        networkService.sendData<Unit>(Endpoints.Onboarding.name)
            .flatMap { Either.Right(Unit) }

    override suspend fun getDashboardData() =
        networkService.getDataResult(Endpoints.PrivateArea.DASHBOARD)

    override suspend fun getNotificationsPage() =
        networkService.getDataResult(Endpoints.PrivateArea.Notifications.name)

    override suspend fun deactivateNotifications(ids: List<Int>?) =
        networkService.sendDataResult(Endpoints.PrivateArea.Notifications.DEACTIVATE, data = ids)

    override suspend fun pinNotification(request: PinNotificationRequest) =
        networkService.sendDataResult(Endpoints.PrivateArea.Notifications.PIN, request)

    override suspend fun deleteNotification(request: DeleteNotificationRequest) =
        networkService.sendDataResult(Endpoints.PrivateArea.Notifications.DELETE, request)

}