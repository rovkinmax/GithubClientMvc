package com.github.rovkinmax.githubclientmvc.view

import com.github.rovkinmax.githubclientmvc.model.Repo
import java.util.*

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

interface RepoListView {
    fun updateRepoList(repoList: ArrayList<Repo>)
    fun showError(message: String)
}