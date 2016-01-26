package com.github.rovkinmax.githubclientmvc.utils

import android.os.Bundle
import org.mockito.Mockito

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