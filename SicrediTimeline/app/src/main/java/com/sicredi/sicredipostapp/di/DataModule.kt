package com.sicredi.sicredipostapp.di

import com.sicredi.sicredipostapp.data.network.RetrofitService
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.data.repository.EventRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory { RetrofitService() }
    factory<EventRepository> { EventRepositoryImpl(get()) }
}