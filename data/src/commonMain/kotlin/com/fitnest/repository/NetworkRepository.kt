package com.fitnest.repository

import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.request.DeleteActivityRequest
import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.entity.request.ForgetPasswordRequest
import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.service.NetworkService
import com.fitnest.network.Endpoints

class NetworkRepository(
    private val networkService: NetworkService,
) : NetworkRepository {

    override suspend fun generateToken() = networkService.getData(Endpoints.Flow.name)

    override suspend fun getOnboardingStep() =
        networkService.getData(Endpoints.Onboarding.name)

    override suspend fun getRegistrationStepData() =
        networkService.getData(Endpoints.Registration.name)

    override suspend fun submitRegistrationStep(request: BaseRequest) {
        networkService.sendData(Endpoints.Registration.name, data = request)
    }

    override suspend fun submitOnboardingStep() {
        networkService.sendData(Endpoints.Onboarding.name)
    }

    override suspend fun getDashboardData() =
        networkService.getData(Endpoints.PrivateArea.DASHBOARD)

    override suspend fun getNotificationsPage() =
        networkService.getData(Endpoints.PrivateArea.Notifications.name)

    override suspend fun getActivityTrackerPage() =
        networkService.getData(Endpoints.PrivateArea.ActivityTracker.name)

    override suspend fun deactivateNotifications(ids: List<Int>?) =
        networkService.sendData(Endpoints.PrivateArea.Notifications.DEACTIVATE, data = ids)

    override suspend fun pinNotification(request: PinNotificationRequest) =
        networkService.sendData(Endpoints.PrivateArea.Notifications.PIN, request)

    override suspend fun deleteNotification(request: DeleteNotificationRequest) =
        networkService.sendData(Endpoints.PrivateArea.Notifications.DELETE, request)

    override suspend fun getLoginPage() = networkService.getData(Endpoints.Auth.LOGIN)

    override suspend fun loginUser(request: LoginPageResponse.LoginPageFields) =
        networkService.sendData(Endpoints.Auth.LOGIN, request)

    override suspend fun forgetPassword(request: ForgetPasswordRequest) =
        networkService.sendData(Endpoints.Auth.FORGET_PASSWORD, request)

    override suspend fun deleteActivity(request: DeleteActivityRequest) =
        networkService.sendData(
            Endpoints.PrivateArea.ActivityTracker.DELETE_ACTIVITY,
            request
        )

    override suspend fun addActivity(request: AddActivityRequest) =
        networkService.sendData(Endpoints.PrivateArea.ActivityTracker.ADD_ACTIVITY, request)
}
