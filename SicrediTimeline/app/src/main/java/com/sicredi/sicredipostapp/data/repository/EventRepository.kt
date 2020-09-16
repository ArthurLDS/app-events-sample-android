package com.sicredi.sicredipostapp.data.repository

import com.sicredi.sicredipostapp.data.network.RetrofitService
import com.sicredi.sicredipostapp.data.model.Event

class EventRepository() {
    suspend fun getEvents(): List<Event> = RetrofitService.service.getEvents()
}