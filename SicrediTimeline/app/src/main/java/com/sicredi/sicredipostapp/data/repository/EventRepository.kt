package com.sicredi.sicredipostapp.data.repository

import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel

interface EventRepository {
    suspend fun getEvents(): List<EventModel>
    suspend fun getEvent(id: String): EventModel
    suspend fun postCheckIn(checkIn: CheckInModel)
}