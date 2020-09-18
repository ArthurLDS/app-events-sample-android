package com.sicredi.sicredipostapp.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventDetailViewModel(private val repository: EventRepository) : ViewModel() {

    val eventDetailLiveData: MutableLiveData<EventModel> = MutableLiveData()
    val checkInResultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEvent(idEvent: String) {
        viewModelScope.launch {
            try {
                eventDetailLiveData.value = repository.getEvent(idEvent)
            } catch (ex: Exception) {
                print("API Error: ${ex.message}")
            }
            /*eventDetailLiveData.value =
                EventModel(id = "1", title="Evento 1", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
                    longitude = "3423434", latitude = "324234234", image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"
                )*/
        }
    }

    fun postCheckIn(eventId: String, userName: String, userLastname: String) {
        viewModelScope.launch {
            val checkIn = CheckInModel(eventId, userName, userLastname)
            try {
                checkInResultLiveData.value = true
                repository.postCheckIn(checkIn)
            } catch (ex: Exception) {
                checkInResultLiveData.value = false
                print("API Error: ${ex.message}")
            }
        }
    }
}