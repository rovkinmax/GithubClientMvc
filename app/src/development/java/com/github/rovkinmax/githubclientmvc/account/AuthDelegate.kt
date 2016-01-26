package com.github.rovkinmax.githubclientmvc.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import com.github.rovkinmax.githubclientmvc.BuildConfig

/**
 * @author Rovkin Max
 */
class AuthDelegate(context: Context) {
    private val accountManager: AccountManager

    init {
        accountManager = AccountManager.get(context)
    }

    fun checkAuth(account: Account?, authCallback: AuthCallback) {
        if (account != null)
            authCallback.onAuthSuccessful(account, "1234567890")
        else
            authCallback.onAuthFailed()
    }

    fun tryLogin(login: String, password: String, authCallback: AuthCallback) {
        val account = getOrCreateAccount(login, password)
        val bundle = Bundle()
        bundle.putString(AccountManager.KEY_PASSWORD, password)
        accountManager.getAuthToken(account, account.type, bundle, false, { future ->
            try {
                val result = future.result
                val token: String? = future.result.getString(AccountManager.KEY_AUTHTOKEN)
                if (!token.isNullOrBlank()) {
                    authCallback.onAuthSuccessful(account, token!!)
                } else {
                    authCallback.onAuthFailed()
                }
            } catch(e: Exception) {
                authCallback.onAuthFailed()
            }
        }, null)
    }

    private fun getOrCreateAccount(login: String, password: String): Account {
        var account = accountManager.accounts.filter { it.name.equals(login) }.firstOrNull()
        if (account == null) {
            account = Account(login, BuildConfig.ACCOUNT_TYPE)
            accountManager.addAccountExplicitly(account, password, Bundle.EMPTY)
        }
        return account
    }

}

interface AuthCallback {
    fun onAuthSuccessful(account: Account, token: String)
    fun onAuthFailed()
}