package com.github.rovkinmax.githubclientmvc.view

import com.github.rovkinmax.githubclientmvc.model.Repo
import java.util.*

/**
 * @author Rovkin Max
 */

interface LoadingView {
    fun showProgress()
    fun hideProgress()
}

interface SplashView {
}

interface LoginView : LoadingView {
    fun loginAllowed()
    fun loginDeny()
}

interface RepoListView : LoadingView {
    fun updateRepoList(repoList: ArrayList<Repo>)
    fun showError(message: String)
}