package com.sicredi.sicredipostapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.ui.events.EventsActivity


class SplashActivity : AppCompatActivity() {

    private val splashTimeout : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        val handler = Handler()
        handler.postDelayed(Runnable { showEventsActivity() }, splashTimeout)
    }

    private fun showEventsActivity() {
        val intent = Intent(this@SplashActivity, EventsActivity::class.java)
        startActivity(intent)
        finish()
    }
}