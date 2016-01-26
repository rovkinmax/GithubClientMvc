package com.github.rovkinmax.githubclientmvc.presenter

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
class LoginPresenterTest {
    private lateinit var view: LoginView
    private lateinit var presenter: LoginPresenter
    @Before
    fun setUp() {
        view = Mockito.mock(LoginView::class.java)
        presenter = LoginPresenter(view)
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
}

class LoginPresenter(val view: LoginView) {
    fun validateCredentials(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank())
            view.loginDeny()
        else
            view.loginAllowed()
    }
}

interface LoginView {
    fun loginAllowed()

    fun loginDeny()
}
