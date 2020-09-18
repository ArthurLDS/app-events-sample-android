package com.sicredi.sicredipostapp.di

import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailViewModel
import com.sicredi.sicredipostapp.ui.events.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}