package com.github.rovkinmax.githubclientmvc

import com.github.rovkinmax.githubclientmvc.api.ApiError

/**
 * @author Rovkin Max
 */
class MockException(val apiError: ApiError) : Throwable() {
}