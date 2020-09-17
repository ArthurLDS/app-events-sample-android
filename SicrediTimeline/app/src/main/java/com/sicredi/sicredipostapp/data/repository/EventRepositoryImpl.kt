package com.sicredi.sicredipostapp.data.repository

import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.network.RetrofitService

class EventRepositoryImpl: EventRepository{

    override suspend fun getEvents(): List<EventModel> = RetrofitService.service.getEvents()

    override suspend fun getEvent(id: String): EventModel = RetrofitService.service.getEvent(id)

    override suspend fun postCheckIn(checkIn: CheckInModel) = RetrofitService.service.postCheckIn(checkIn)
}