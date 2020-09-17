package com.sicredi.sicredipostapp.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventRepository) : ViewModel() {

    val eventsLiveData: MutableLiveData<List<EventModel>> = MutableLiveData()

    fun getEvents() {
        viewModelScope.launch {
            try {
                eventsLiveData.value = repository.getEvents()
                /*eventsLiveData.value = listOf(
                    EventModel(id = "1", title="Evento 1", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
                        longitude = "3423434", latitude = "324234234", image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"),
                    EventModel(id = "2", title="Evento 2", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
                        longitude = "3423434", latitude = "234234234", image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"),
                    EventModel(id = "3", title="Evento 3", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
                        longitude = "3423434", latitude = "sdf234234", image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg")
                )*/
            } catch (e: Exception) {
                print("Api error: ${e.message}")
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