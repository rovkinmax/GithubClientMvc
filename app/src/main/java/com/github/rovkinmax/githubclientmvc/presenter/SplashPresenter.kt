package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.github.rovkinmax.githubclientmvc.account.AuthCallback
import com.github.rovkinmax.githubclientmvc.account.AuthDelegate
import com.github.rovkinmax.githubclientmvc.view.SplashView

/**
 * @author Rovkin Max
 */
class SplashPresenter(private val context: Context, private val view: SplashView) {
    private val accountManager: AccountManager;
    private val authDelegate: AuthDelegate

    init {
        accountManager = AccountManager.get(context)
        authDelegate = AuthDelegate(context)
    }

    fun checkAndContinue() {
        val account = accountManager.accounts.firstOrNull()
        if (account == null) continueWithoutAccount()
        else {
            authDelegate.checkAuth(account, object : AuthCallback {
                override fun onAuthSuccessful(account: Account, token: String) {
                    continueWithAccount()
                }

                override fun onAuthFailed() {
                    continueWithAccount()
                }
            })
        }
    }

    open fun continueWithAccount() {

    }

    open fun continueWithoutAccount() {

    }
}