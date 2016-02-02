package com.github.rovkinmax.githubclientmvc

import com.github.rovkinmax.githubclientmvc.api.AuthService

object ServiceFactory {
    fun provideAuthService(): AuthService {
        return MockAuthService()
    }
}