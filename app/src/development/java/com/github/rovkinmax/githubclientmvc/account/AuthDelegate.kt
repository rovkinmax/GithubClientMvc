package com.github.rovkinmax.githubclientmvc.account

import android.accounts.Account
import android.content.Context

/**
 * @author Rovkin Max
 */
class AuthDelegate(context: Context) {
    fun checkAuth(account: Account?, authCallback: AuthCallback) {
        if (account != null)
            authCallback.onAuthSuccessful(account, "1234567890")
        else
            authCallback.onAuthFailed()
    }
}

interface AuthCallback {
    fun onAuthSuccessful(account: Account, token: String)
    fun onAuthFailed()
}