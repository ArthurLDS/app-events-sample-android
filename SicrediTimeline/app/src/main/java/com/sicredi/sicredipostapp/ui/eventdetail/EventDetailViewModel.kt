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
    val loadingDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingCheckInLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEvent(idEvent: String) {
        loadingDetailsLiveData.value = true
        viewModelScope.launch {
            try {
                eventDetailLiveData.value = repository.getEvent(idEvent)
            } catch (ex: Exception) {
                print("API Error: ${ex.message}")
            }
            loadingDetailsLiveData.value = false
        }
    }

    fun postCheckIn(eventId: String, userName: String, userLastname: String) {
        loadingCheckInLiveData.value = true
        viewModelScope.launch {
            val checkIn = CheckInModel(eventId, userName, userLastname)
            try {
                repository.postCheckIn(checkIn)
                checkInResultLiveData.value = true
            } catch (ex: Exception) {
                checkInResultLiveData.value = false
                print("API Error: ${ex.message}")
            }
            loadingCheckInLiveData.value = false
        }
    }
}