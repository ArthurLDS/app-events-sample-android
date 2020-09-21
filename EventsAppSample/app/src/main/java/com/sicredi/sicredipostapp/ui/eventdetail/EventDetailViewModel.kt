package com.sicredi.sicredipostapp.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.ui.util.ValidationUtil
import kotlinx.coroutines.launch

class EventDetailViewModel(private val repository: EventRepository) : ViewModel() {

    val eventDetailLiveData: MutableLiveData<EventModel> = MutableLiveData()
    val loadingDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val checkInResultLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingCheckInLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorEventDetailLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEvent(idEvent: String) {
        loadingDetailsLiveData.value = true
        errorEventDetailLiveData.value = false
        viewModelScope.launch {
            try {
                eventDetailLiveData.value = repository.getEvent(idEvent)
            } catch (ex: Exception) {
                errorEventDetailLiveData.value = true
            }
            loadingDetailsLiveData.value = false
        }
    }

    fun postCheckIn(eventId: String, userName: String, userLastName: String) {
        loadingCheckInLiveData.value = true
        viewModelScope.launch {
            val checkIn = CheckInModel(eventId, userName, userLastName)
            try {
                repository.postCheckIn(checkIn)
                checkInResultLiveData.value = true
            } catch (ex: Exception) {
                checkInResultLiveData.value = false
            }
            loadingCheckInLiveData.value = false
        }
    }

    fun getShareText(): String {
        var shareText = "${eventDetailLiveData.value?.title}\n\n"
        shareText += eventDetailLiveData.value?.description
        return shareText
    }

    fun validateText(text: String) = text.isNotEmpty() && text.isNotBlank()

    fun validateEmail(email: String) = ValidationUtil.isEmailValid(email)

}