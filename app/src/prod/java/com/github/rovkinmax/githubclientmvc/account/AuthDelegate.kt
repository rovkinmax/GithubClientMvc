package com.github.rovkinmax.githubclientmvc.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle

/**
 * @author Rovkin Max
 */
class AuthDelegate(context: Context) {
    private val accountManager: AccountManager

    init {
        accountManager = AccountManager.get(context)
    }

    fun checkAuth(account: Account, authCallback: AuthCallback) {
        getToken(account, Bundle.EMPTY, authCallback)
    }

    private fun getToken(account: Account, options: Bundle, callback: AuthCallback) {
        accountManager.getAuthToken(account, account.type, options, false, { future ->
            try {
                val result = future.result
                if (result.containsKey(AccountManager.KEY_AUTHTOKEN)) {
                    val token = result.getString(AccountManager.KEY_AUTHTOKEN)
                    callback.onAuthSuccessful(account, token)

                } else {
                    callback.onAuthFailed()
                }
            } catch (e: Exception) {
                callback.onAuthFailed()
            }
        }, null)
    }
}

interface AuthCallback {
    fun onAuthSuccessful(account: Account, token: String)
    fun onAuthFailed()
}