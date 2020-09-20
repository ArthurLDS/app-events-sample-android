package com.sicredi.sicredipostapp.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val eventsLiveData: MutableLiveData<List<EventModel>> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEvents() {
        loadingLiveData.value = true
        viewModelScope.launch {
            try {
                eventsLiveData.value = repository.getEvents()
            } catch (e: Exception) {
                print("Api error: ${e.message}")
            }
            loadingLiveData.value = false
        }
    }
}