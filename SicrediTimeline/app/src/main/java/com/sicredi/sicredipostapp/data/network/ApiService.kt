package com.sicredi.sicredipostapp.data.network

import com.sicredi.sicredipostapp.data.model.Event
import retrofit2.http.GET

interface ApiService {
    @GET("/events")
    suspend fun getEvents(): List<Event>
}