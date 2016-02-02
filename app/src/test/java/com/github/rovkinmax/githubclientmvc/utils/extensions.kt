package com.github.rovkinmax.githubclientmvc.utils

import android.os.Bundle
import com.github.rovkinmax.githubclientmvc.ApiErrorCodec
import com.github.rovkinmax.githubclientmvc.api.ApiError
import org.junit.Assert
import org.mockito.Mockito
import rx.functions.Action1
import rx.observers.TestSubscriber

/**
 * @author Rovkin Max
 */
fun<T> T.verify() = Mockito.verify(this)

fun emptyBundle(): Bundle {
    val bundle = Mockito.mock(Bundle::class.java)
    Mockito.`when`(bundle.getString(Mockito.any())).thenReturn("")
    Mockito.`when`(bundle.getString(Mockito.any(), Mockito.any())).thenReturn("")
    return bundle;
}

fun <T> TestSubscriber<T>.callOnError(onError: Action1<Throwable>): Unit {
    val throwable = onErrorEvents.firstOrNull()
    if (throwable == null) throw AssertionError("No errors")
    else onError.call(throwable)
}

fun <T> TestSubscriber<T>.assertWithErrorCodec(apiError: ApiError) {
    callOnError(ApiErrorCodec { Assert.assertEquals(apiError, it) })
}