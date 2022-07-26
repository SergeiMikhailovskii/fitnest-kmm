package com.fitnest.android.internal

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.fitnest.android.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

internal class GoogleSignInService(private val context: Context) {

    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var onSuccess: ((GoogleSignInAccount) -> Unit)? = null

    internal fun init() {
        startForResult =
            (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    if (result.data != null) {
                        val task = GoogleSignIn.getSignedInAccountFromIntent(intent).result
                        onSuccess?.invoke(task)
                    }
                }
            }
    }

    internal fun login(onSuccess: (GoogleSignInAccount) -> Unit) {
        this.onSuccess = onSuccess
        val intent = getSignInClient(context).signInIntent
        startForResult.launch(intent)
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