package com.sicredi.sicredipostapp.ui.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailActivity
import kotlinx.android.synthetic.main.events_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)
        setUpToolbar()

        viewModel.eventsLiveData.observe(this, Observer {
            it?.let { posts ->
                with(recyclerview_post_list) {
                    layoutManager =
                        LinearLayoutManager(this@EventsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = EventsAdapter(posts) {
                        EventDetailActivity.start(this@EventsActivity, it.id)
                    }
                }
            }
        })
        viewModel.getEvents()
    }

    private fun setUpToolbar() {
        toolbar_event_detail.title = getString(R.string.activity_events_title);
    }
}