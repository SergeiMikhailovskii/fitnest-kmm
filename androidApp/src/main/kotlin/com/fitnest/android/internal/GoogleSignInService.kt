package com.fitnest.android.internal

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.fitnest.android.BuildConfig
import com.fitnest.domain.entity.thirdparty.FitnestSignInAccount
import com.fitnest.presentation.internal.GoogleSignInService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

internal class GoogleSignInService : GoogleSignInService {

    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var onSuccess: ((FitnestSignInAccount) -> Unit)? = null
    private var activity: ComponentActivity? = null

    override fun login(onSuccess: (FitnestSignInAccount) -> Unit) {
        this.onSuccess = onSuccess
        activity?.let {
            val intent = getSignInClient(it).signInIntent
            startForResult.launch(intent)
        }
    }

    internal fun init(activity: ComponentActivity) {
        this.activity = activity
        startForResult =
            activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    if (result.data != null) {
                        val taskResult = GoogleSignIn.getSignedInAccountFromIntent(intent).result
                        val model = FitnestSignInAccount(
                            givenName = taskResult.givenName,
                            familyName = taskResult.familyName,
                            email = taskResult.email,
                            idToken = taskResult.idToken
                        )
                        onSuccess?.invoke(model)
                    }
                }
            }
    }

    private fun getSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

}