package com.github.rovkinmax.githubclientmvc.view

/**
 * @author Rovkin Max
 */
interface SplashView {
}

interface LoginView {
    fun loginAllowed()

    fun loginDeny()

    fun showProgress()
}