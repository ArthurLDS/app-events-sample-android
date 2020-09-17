package com.sicredi.sicredipostapp.ui.events

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.data.repository.EventRepositoryImpl
import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailActivity
import kotlinx.android.synthetic.main.events_activity.*

class EventsActivity : AppCompatActivity() {

    private lateinit var viewModel: EventsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)

        setUpToolbar()
        setUpViewModel()

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

    private fun setUpViewModel() {
        viewModel = EventsViewModel.EventsViewModelFactory(EventRepositoryImpl())
            .create(EventsViewModel::class.java)
    }

    private fun setUpToolbar() {
        toolbar_event_detail.title = getString(R.string.activity_events_title);
    }
}