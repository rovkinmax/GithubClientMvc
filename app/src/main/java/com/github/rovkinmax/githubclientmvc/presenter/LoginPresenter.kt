package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.content.Context
import com.github.rovkinmax.githubclientmvc.account.AuthCallback
import com.github.rovkinmax.githubclientmvc.account.AuthDelegate
import com.github.rovkinmax.githubclientmvc.view.LoginView

/**
 * @author Rovkin Max
 */
open class LoginPresenter(context: Context, val view: LoginView) {
    private val authDelegate: AuthDelegate

    init {
        authDelegate = AuthDelegate(context)
    }

    fun validateCredentials(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank())
            view.loginDeny()
        else
            view.loginAllowed()
    }

    fun login(login: String, password: String) {
        view.showProgress()
        tryGetToken(login, password)
    }

    open fun tryGetToken(login: String, password: String) {
        authDelegate.tryLogin(login, password, object : AuthCallback {
            override fun onAuthSuccessful(account: Account, token: String) {
                successfulLogin()
            }

            override fun onAuthFailed() {
                failedLogin()
            }
        })
    }

    open fun successfulLogin() {

    }

    open fun failedLogin() {

    }
}