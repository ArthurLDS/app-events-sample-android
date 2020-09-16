package com.sicredi.sicredipostapp.ui.events

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailActivity
import kotlinx.android.synthetic.main.events_activity.*

class EventsActivity : AppCompatActivity() {

    private lateinit var viewModel: EventsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)
        setUpToolbar()

        viewModel = EventsViewModel.EventsViewModelFactory(EventRepository())
            .create(EventsViewModel::class.java)

        viewModel.eventsLiveData.observe(this, Observer {
            it?.let { posts ->
                with(recyclerview_post_list) {
                    layoutManager =
                        LinearLayoutManager(this@EventsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = EventsAdapter(posts) {
                        val intent =
                            Intent(this@EventsActivity, EventDetailActivity::class.java).apply {
                                putExtra("ID", it.id)
                            }
                        this@EventsActivity.startActivity(intent)
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