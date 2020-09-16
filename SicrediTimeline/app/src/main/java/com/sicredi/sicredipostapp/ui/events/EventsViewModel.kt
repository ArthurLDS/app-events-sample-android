package com.sicredi.sicredipostapp.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sicredi.sicredipostapp.data.model.Event
import com.sicredi.sicredipostapp.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val eventsLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    fun getEvents() {
        viewModelScope.launch {
            try {
                eventsLiveData.value = repository.getEvents()
            } catch (e: Exception) {
                print("Error ao carregar posts: " + e.message)
            }
        }
    }

    class EventsViewModelFactory(
        private val repository: EventRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EventsViewModel(repository) as T
        }

    }

}