package com.sicredi.sicredipostapp.ui.events

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailActivity
import com.sicredi.sicredipostapp.ui.extension.visibleOrGone
import kotlinx.android.synthetic.main.events_activity.*
import kotlinx.android.synthetic.main.view_network_error.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_activity)
        setUpToolbar()

        setUpTryAgainLoadEvents()
        setUpLoading()
        setUpErrorNetwork()
        setUpEventList()
    }

    private fun setUpLoading() {
        viewModel.loadingLiveData.observe(this, Observer { isLoading ->
            view_loading.visibleOrGone(isLoading)
        })
    }

    private fun setUpErrorNetwork() {
        viewModel.errorEventDetailLiveData.observe(this, Observer { hasError ->
            view_network_error_events.visibleOrGone(hasError)
        })
    }

    private fun setUpTryAgainLoadEvents() {
        view_network_error_events.btn_try_again.setOnClickListener {
            viewModel.getEvents()
        }
    }

    private fun setUpEventList() {
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