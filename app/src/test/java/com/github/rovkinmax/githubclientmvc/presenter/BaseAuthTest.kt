package com.github.rovkinmax.githubclientmvc.presenter

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.content.Context
import android.os.Bundle
import com.github.rovkinmax.githubclientmvc.utils.emptyBundle
import org.junit.Before
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.reflect.Whitebox

/**
 * @author Rovkin Max
 */
open class BaseAuthTest {
    lateinit var accountManager: AccountManager
    lateinit var context: Context
    @Before
    open fun setUp() {
        Whitebox.setInternalState(Bundle::class.java, "EMPTY", emptyBundle())
        context = Mockito.mock(Context::class.java)
        accountManager = Mockito.mock(AccountManager::class.java)

        PowerMockito.mockStatic(AccountManager::class.java)
        Mockito.`when`(AccountManager.get(context)).thenReturn(accountManager)
    }

    fun mockGetAuthToken(bundle: Bundle) {
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