package com.github.rovkinmax.githubclientmvc.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.rovkinmax.githubclientmvc.presenter.SplashPresenter

/**
 * @author Rovkin Max
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = SplashPresenter(this);
        presenter.checkAndContinue()
    }
}