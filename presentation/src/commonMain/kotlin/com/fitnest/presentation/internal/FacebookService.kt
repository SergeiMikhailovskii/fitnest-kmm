package com.fitnest.presentation.internal

import com.fitnest.domain.entity.response.FacebookLoginResponse

interface FacebookService {
    fun login(onSuccess: (FacebookLoginResponse) -> Unit)
}
