package com.sicredi.sicredipostapp.ui.eventdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sicredi.sicredipostapp.R
import kotlinx.android.synthetic.main.events_activity.*

class EventDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: EventDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail_activity)

        setUpToolbar()

    }

    private fun setUpToolbar(){
        toolbar_event_detail.title = getString(R.string.activity_event_detail_title);
        setSupportActionBar(toolbar_event_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_event_detail.setNavigationOnClickListener {
            finish()
        }
    }
}