package com.github.rovkinmax.githubclientmvc.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

/**
 * @author Rovkin Max
 */
class LoginActivity : AppCompatActivity() {
    companion object {
        fun buildIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}