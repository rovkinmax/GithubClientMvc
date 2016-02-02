package com.github.rovkinmax.githubclientmvc.api

data class ApiError(val message: String) {
    var type: Kind = Kind.UNEXPECTED
    var httpCode: Int = 0

    companion object {
        fun create(message: String) = ApiError(message)

        fun unexpected(message: String) = ApiError(message).apply { this.type = Kind.UNEXPECTED }
    }

    enum class Kind {
        NETWORK,
        HTTP,
        UNEXPECTED
    }
}