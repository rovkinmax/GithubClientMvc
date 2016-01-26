package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import com.github.rovkinmax.githubclientmvc.BuildConfig
import com.github.rovkinmax.githubclientmvc.view.SplashView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(SplashPresenter::class, AccountManager::class, Bundle::class)
class SplashPresenterTest : BaseAuthTest() {

    private lateinit var presenter: SplashPresenter
    private lateinit var view: SplashView

    @Before
    override fun setUp() {
        super.setUp()

        view = Mockito.mock(SplashView::class.java)
        presenter = Mockito.spy(SplashPresenter(context))
    }

    @Test
    fun testCheckAuthRequires() {
        Mockito.`when`(accountManager.accounts).thenReturn(emptyArray())
        presenter.checkAndContinue()
        Mockito.verify(presenter).continueWithoutAccount()
    }

    @Test
    fun testAuthExist() {
        val bundle = Mockito.mock(Bundle::class.java)
        Mockito.`when`(bundle.getString(Mockito.any())).thenReturn("1234567890")
        mockGetAuthToken(bundle)
        Mockito.`when`(accountManager.accounts).thenReturn(arrayOf(Account("userName", BuildConfig.ACCOUNT_TYPE)))
        presenter.checkAndContinue()
        Mockito.verify(presenter).continueWithAccount()
    }


}