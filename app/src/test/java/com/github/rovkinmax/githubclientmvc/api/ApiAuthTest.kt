package com.github.rovkinmax.githubclientmvc.api

import com.github.rovkinmax.githubclientmvc.ServiceFactory
import com.github.rovkinmax.githubclientmvc.model.User
import com.github.rovkinmax.githubclientmvc.utils.assertWithErrorCodec
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import rx.observers.TestSubscriber

/**
 * @author Rovkin Max
 */
@RunWith(PowerMockRunner::class)
class ApiAuthTest {

    private lateinit var authService: AuthService
    @Before
    fun setUp() {
        authService = ServiceFactory.provideAuthService()
    }

    @Test
    fun testLoginSuccessful() {
        val subscriber = TestSubscriber<User>()
        authService.login("login", "password")
                .subscribe(subscriber)
        subscriber.assertCompleted()
        subscriber.assertReceivedOnNext(arrayListOf(User("login")))
    }

    @Test
    fun testLoginIncorrect() {
        val subscriber = TestSubscriber<User>()
        authService.login("failed: login", "password")
                .subscribe(subscriber)
        subscriber.assertWithErrorCodec(ApiError("incorrect credentials"))
    }

    @Test
    fun testPassIncorrect() {
        val subscriber = TestSubscriber<User>()
        authService.login("login", "failed: password")
                .subscribe(subscriber)
        subscriber.assertWithErrorCodec(ApiError("incorrect credentials"))
    }

    @Test
    fun testNetworkProblem() {
        val subscriber = TestSubscriber<User>()
        authService.login("network fail", "password")
                .subscribe(subscriber)
        subscriber.assertWithErrorCodec(ApiError("Network is unreachable"))
    }
}