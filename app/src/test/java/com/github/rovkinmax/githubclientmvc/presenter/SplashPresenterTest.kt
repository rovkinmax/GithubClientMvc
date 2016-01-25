package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.github.rovkinmax.githubclientmvc.BuildConfig
import com.github.rovkinmax.githubclientmvc.view.SplashView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.rule.PowerMockRule

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(SplashPresenter::class, AccountManager::class)
class SplashPresenterTest {
    @get:Rule
    val rule = PowerMockRule()

    private lateinit var context: Context
    private lateinit var presenter: SplashPresenter
    private lateinit var view: SplashView
    private lateinit var accountManager: AccountManager

    @Before
    fun setUp() {
        view = Mockito.mock(SplashView::class.java)
        context = Mockito.mock(Context::class.java)
        accountManager = Mockito.mock(AccountManager::class.java)

        PowerMockito.mockStatic(AccountManager::class.java)
        Mockito.`when`(AccountManager.get(context)).thenReturn(accountManager)
        presenter = Mockito.spy(SplashPresenter(context, view))
    }

    @Test
    fun testCheckAuthRequires() {
        Mockito.`when`(accountManager.accounts).thenReturn(emptyArray())
        presenter.checkAndContinue()
        Mockito.verify(presenter).continueWithoutAccount()
    }

    @Test
    fun testAuthExist() {
        Mockito.`when`(accountManager.accounts).thenReturn(arrayOf(Account("userName", BuildConfig.ACCOUNT_TYPE)))
        presenter.checkAndContinue()
        Mockito.verify(presenter).continueWithAccount()
    }
}