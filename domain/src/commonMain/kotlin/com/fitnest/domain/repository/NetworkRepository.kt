package com.fitnest.domain.repository

import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.request.DeleteActivityRequest
import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.entity.request.ForgetPasswordRequest
import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.entity.response.LoginPageResponse

interface NetworkRepository {

    suspend fun generateToken(): BaseResponse

    suspend fun getOnboardingStep(): BaseResponse

    suspend fun submitOnboardingStep()

    suspend fun getRegistrationStepData(): BaseResponse

    suspend fun submitRegistrationStep(request: BaseRequest)

    suspend fun getDashboardData(): BaseResponse

    suspend fun getNotificationsPage(): BaseResponse

    suspend fun getActivityTrackerPage(): BaseResponse

    suspend fun deactivateNotifications(ids: List<Int>?): BaseResponse

    suspend fun pinNotification(request: PinNotificationRequest): BaseResponse

    suspend fun deleteNotification(request: DeleteNotificationRequest): BaseResponse

    suspend fun getLoginPage(): BaseResponse

    suspend fun loginUser(request: LoginPageResponse.LoginPageFields): BaseResponse

    suspend fun forgetPassword(request: ForgetPasswordRequest): BaseResponse

    suspend fun deleteActivity(request: DeleteActivityRequest): BaseResponse

    suspend fun addActivity(request: AddActivityRequest): BaseResponse
}
