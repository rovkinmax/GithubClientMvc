package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.content.Context
import android.os.Bundle
import com.github.rovkinmax.githubclientmvc.utils.emptyBundle
import com.github.rovkinmax.githubclientmvc.utils.verify
import com.github.rovkinmax.githubclientmvc.view.LoginView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(LoginPresenter::class, AccountManager::class)
class LoginPresenterTest {


    private lateinit var view: LoginView
    private lateinit var presenter: LoginPresenter
    private lateinit var accountManager: AccountManager
    @Before
    fun setUp() {
        val context = Mockito.mock(Context::class.java)
        view = Mockito.mock(LoginView::class.java)

        accountManager = Mockito.mock(AccountManager::class.java)
        Mockito.`when`(accountManager.accounts).thenReturn(emptyArray())
        PowerMockito.mockStatic(AccountManager::class.java)
        Mockito.`when`(AccountManager.get(context)).thenReturn(accountManager)

        presenter = Mockito.spy(LoginPresenter(context, view))
    }

    @Test
    fun testValidateCredentials() {
        presenter.validateCredentials("login", "password")
        Mockito.verify(view).loginAllowed()
    }

    @Test
    fun testEmptyCredentials() {
        presenter.validateCredentials("", "")
        Mockito.verify(view).loginDeny()
    }

    @Test
    fun testLoginNullable() {
        presenter.validateCredentials(null, "pass")
        Mockito.verify(view).loginDeny()
    }

    @Test
    fun testPassNullable() {
        presenter.validateCredentials("login", null)
        Mockito.verify(view).loginDeny()
    }

    @Test
    fun testNullableCredentials() {
        presenter.validateCredentials(null, null)
        Mockito.verify(view).loginDeny()
    }

    @Test
    fun testLoginSuccessful() {
        val bundle = Mockito.mock(Bundle::class.java)
        Mockito.`when`(bundle.getString(Mockito.any())).thenReturn("1234567890")
        Mockito.`when`(bundle.getString(Mockito.any(), Mockito.any())).thenReturn("")
        mockGetAuthToken(bundle)
        presenter.login("login", "pass")
        Mockito.verify(view).showProgress()
        Mockito.verify(presenter).tryGetToken("login", "pass")
        Mockito.verify(presenter).successfulLogin()
    }

    @Test
    fun testLoginFailed() {
        mockGetAuthToken(emptyBundle())
        presenter.login("login", "incorrect pass")
        Mockito.verify(view).showProgress()
        presenter.verify().tryGetToken("login", "incorrect pass")
        presenter.verify().failedLogin()
    }

    private fun mockGetAuthToken(bundle: Bundle) {
        val future = Mockito.mock(AccountManagerFuture::class.java)
        Mockito.`when`(future.result).thenReturn(bundle)
        Mockito.`when`(accountManager.getAuthToken(Mockito.any(Account::class.java), Mockito.anyString(), Mockito.any(Bundle::class.java), Mockito.anyBoolean(), Mockito.any(), Mockito.any()))
                .thenAnswer { mock ->
                    val callbackParam = mock.getArgumentAt(4, AccountManagerCallback::class.java)
                    callbackParam.run(future)
                    null
                }
    }
}

