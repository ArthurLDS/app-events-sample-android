package com.sicredi.sicredipostapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.ui.events.EventsViewModel
import com.sicredi.sicredipostapp.testUtil.TestData.EVENT_LIST
import com.sicredi.sicredipostapp.testUtil.TestData.IS_LOADING
import com.sicredi.sicredipostapp.testUtil.TestData.NOT_LOADING
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<EventRepository>()
    private val eventsLiveDataObserver = mockk<Observer<List<EventModel>>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when get events with success then set eventsLiveData and show loading`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.getEvents() } returns EVENT_LIST

        viewModel.getEvents()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(IS_LOADING)
            repository.getEvents()
            eventsLiveDataObserver.onChanged(EVENT_LIST)
            loadingLiveDataObserver.onChanged(NOT_LOADING)
        }
        confirmVerified(loadingLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(eventsLiveDataObserver)
    }

    @Test
    fun `when get events with error then show error and loading`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.getEvents() } throws Exception()

        viewModel.getEvents()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(IS_LOADING)
            repository.getEvents()
            loadingLiveDataObserver.onChanged(NOT_LOADING)
        }
        confirmVerified(loadingLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(loadingLiveDataObserver)
    }

    private fun instantiateViewModel(): EventsViewModel {
        val viewModel =
            EventsViewModel(repository)
        viewModel.eventsLiveData.observeForever(eventsLiveDataObserver)
        viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
        return viewModel
    }

}