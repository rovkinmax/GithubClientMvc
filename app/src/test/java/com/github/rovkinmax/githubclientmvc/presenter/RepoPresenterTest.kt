package com.github.rovkinmax.githubclientmvc.presenter

import com.github.rovkinmax.githubclientmvc.api.ApiError
import com.github.rovkinmax.githubclientmvc.model.Repo
import com.github.rovkinmax.githubclientmvc.view.RepoListView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
class RepoPresenterTest {

    private lateinit var view: RepoListView
    private lateinit var presenter: RepoPresenter

    @Before
    fun setUp() {
        view = Mockito.mock(RepoListView::class.java)
        presenter = RepoPresenter(view)
    }

    @Test
    fun testLoadRepoList() {
        presenter.dispatchRepoList(arrayListOf(Repo("1")))
        Mockito.verify(view).updateRepoList(arrayListOf(Repo("1")))
    }

    @Test
    fun testDispatchError() {
        val error = ApiError.create("server error")
        presenter.dispatchError(error)
        Mockito.verify(view).showError("server error")
    }
}