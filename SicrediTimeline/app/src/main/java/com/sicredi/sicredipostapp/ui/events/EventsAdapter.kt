package com.sicredi.sicredipostapp.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.ui.extension.loadImage
import kotlinx.android.synthetic.main.event_item.view.*

class EventsAdapter(
    private val events: List<EventModel>,
    private val onItemClick: ((event: EventModel) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): PostsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return PostsViewHolder(itemView, onItemClick)
    }

    override fun getItemCount() = events.count()

    override fun onBindViewHolder(viewHolder: PostsViewHolder, position: Int) {
        viewHolder.bindView(events[position])
    }

    class PostsViewHolder(view: View, val onItemClick: ((event: EventModel) -> Unit)) : RecyclerView.ViewHolder(view) {
        private val title = itemView.tv_title
        private val subtitle = itemView.tv_subtitle
        private val image = itemView.iv_post_image

        fun bindView(event: EventModel) {
            title.text = event.title
            subtitle.text = event.description
            image.loadImage(event.image)

            itemView.setOnClickListener {
                onItemClick.invoke(event)
            }
        }
    }
}