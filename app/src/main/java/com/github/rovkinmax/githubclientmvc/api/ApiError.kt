package com.github.rovkinmax.githubclientmvc.api

class ApiError(val message: String) {
    companion object {
        fun create(message: String) = ApiError(message)
    }
}