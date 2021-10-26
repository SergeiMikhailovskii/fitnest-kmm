package com.fitnest.domain.repository

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure

interface NetworkRepository {

    fun loginUser(data: LoginData)

    fun generateToken(): Either<Failure, String>

}