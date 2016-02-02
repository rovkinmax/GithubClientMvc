package com.github.rovkinmax.githubclientmvc

import com.github.rovkinmax.githubclientmvc.api.ApiError
import com.github.rovkinmax.githubclientmvc.api.AuthService
import com.github.rovkinmax.githubclientmvc.model.User
import rx.Observable

class MockAuthService : AuthService {
    override fun login(login: String, password: String): Observable<User> {

        if (login.contains("failed".toRegex()) || password.contains("failed".toRegex()))
            return Observable.error(MockException(ApiError.create("incorrect credentials")))

        if (login.contains("network fail"))
            return Observable.error(MockException(ApiError.create("Network is unreachable")))

        val user = User("login")
        return Observable.just(user)
    }
}