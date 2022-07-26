package com.fitnest.android.internal

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.fitnest.domain.entity.response.FacebookLoginResponse


internal class FacebookService(
    private val context: Context
) {

    internal fun login(onSuccess: (FacebookLoginResponse) -> Unit) {
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                    error.printStackTrace()
                }

                override fun onSuccess(result: LoginResult) {
                    val request = GraphRequest.newMeRequest(
                        result.accessToken
                    ) { _, response ->
                        val id = response?.jsonObject?.getString("id")
                        val email = response?.jsonObject?.getString("email")
                        val firstName = response?.jsonObject?.getString("first_name")
                        val lastName = response?.jsonObject?.getString("last_name")
                        val fbResponse = FacebookLoginResponse(
                            id = id,
                            email = email,
                            firstName = firstName,
                            lastName = lastName
                        )
                        onSuccess(fbResponse)
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,email,first_name,last_name")
                    request.parameters = parameters
                    request.executeAsync()
                }
            })

        LoginManager.getInstance()
            .logIn(context as ComponentActivity, callbackManager, listOf("email"))

    }

}