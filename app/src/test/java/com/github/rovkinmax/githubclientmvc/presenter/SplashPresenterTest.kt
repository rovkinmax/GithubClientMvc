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
import org.powermock.api.support.membermodification.MemberModifier
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * @author Rovkin Max
 */
@RunWith(RobolectricGradleTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@PrepareForTest(SplashPresenter::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class SplashPresenterTest {
    @get:Rule
    var rule = PowerMockRule()

    private lateinit var context: Context
    private lateinit var presenter: SplashPresenter
    private lateinit var view: SplashView
    private lateinit var accountManager: AccountManager

    @Before
    fun setUp() {
        context = RuntimeEnvironment.application
        view = Mockito.mock(SplashView::class.java)

        accountManager = Mockito.mock(AccountManager::class.java)
        val tempPresenter = SplashPresenter(context, view)
        MemberModifier.field(SplashPresenter::class.java, "accountManager").set(tempPresenter, accountManager)
        presenter = Mockito.spy(tempPresenter)

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