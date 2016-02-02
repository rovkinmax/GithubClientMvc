package com.github.rovkinmax.githubclientmvc

import com.github.rovkinmax.githubclientmvc.api.ApiError
import com.google.gson.GsonBuilder
import rx.functions.Action1

/**
 * @author Rovkin Max
 */
class ApiErrorCodec(private val func: (ApiError) -> (Unit)) : Action1<Throwable> {

    companion object {
        private val GSON = GsonBuilder().create()
        private val UNEXPECTED = ApiError.unexpected("")
    }

    override fun call(t: Throwable?) {
        func(t.toApiError())
    }

    private fun Throwable?.toApiError(): ApiError {
        if (this == null) return UNEXPECTED
        if (this is MockException) return apiError
        return message.toApiError()
    }

    fun String?.toApiError(): ApiError {
        try {
            return GSON.fromJson(this, ApiError::class.java)
        } catch(e: Exception) {
            return UNEXPECTED
        }
    }
}




