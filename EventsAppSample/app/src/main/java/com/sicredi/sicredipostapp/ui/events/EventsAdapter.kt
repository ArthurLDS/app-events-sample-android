package com.sicredi.sicredipostapp.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.ui.extension.loadImage
import com.sicredi.sicredipostapp.ui.extension.toDateFormatted
import kotlinx.android.synthetic.main.event_item.view.*

class EventsAdapter(
    private val events: List<EventModel>,
    private val onItemClick: ((event: EventModel) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): EventsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventsViewHolder(itemView, onItemClick)
    }

    override fun getItemCount() = events.count()

    override fun onBindViewHolder(viewHolder: EventsViewHolder, position: Int) {
        viewHolder.bindView(events[position])
    }

    class EventsViewHolder(view: View, private val onItemClick: ((event: EventModel) -> Unit)) :
        RecyclerView.ViewHolder(view) {
        private val title = itemView.tv_title
        private val subtitle = itemView.tv_subtitle
        private val image = itemView.iv_post_image
        private val date = itemView.tv_date

        fun bindView(event: EventModel) {
            title.text = event.title
            subtitle.text = event.description
            image.loadImage(event.image)
            date.text = event.date.toDateFormatted()

            itemView.setOnClickListener {
                onItemClick.invoke(event)
            }
        }

    }
}