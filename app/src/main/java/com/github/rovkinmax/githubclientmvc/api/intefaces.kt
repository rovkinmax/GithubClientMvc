package com.github.rovkinmax.githubclientmvc.api

import com.github.rovkinmax.githubclientmvc.model.User
import rx.Observable

/**
 * @author Rovkin Max
 */
interface AuthService {
    fun login(login: String, password: String): Observable<User>
}