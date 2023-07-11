package com.fitnest.presentation.internal

import com.fitnest.domain.entity.thirdparty.FitnestSignInAccount

interface GoogleSignInService {
    fun login(onSuccess: (FitnestSignInAccount) -> Unit)
}
