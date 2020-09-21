package com.sicredi.sicredipostapp.data.model

data class EventModel(
    val id: String,
    val title: String,
    val description: String,
    val longitude: String,
    val latitude: String,
    val date: Long,
    val price: Float,
    val image: String
)