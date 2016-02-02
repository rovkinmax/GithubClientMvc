package com.github.rovkinmax.githubclientmvc.presenter

import com.github.rovkinmax.githubclientmvc.api.ApiError
import com.github.rovkinmax.githubclientmvc.model.Repo
import com.github.rovkinmax.githubclientmvc.view.RepoListView
import java.util.*

class RepoPresenter(val view: RepoListView) {

    fun dispatchError(apiError: ApiError) {
        view.showError(apiError.message)
    }

    fun dispatchRepoList(repoList: ArrayList<Repo>) {
        view.updateRepoList(repoList)
    }

    fun loadRepoList() {
        view.showProgress()
    }
}