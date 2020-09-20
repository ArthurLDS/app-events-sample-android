package com.sicredi.sicredipostapp.data

import com.sicredi.sicredipostapp.data.network.RetrofitService
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.data.repository.EventRepositoryImpl
import com.sicredi.sicredipostapp.testUtil.TestData.EVENT_LIST
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class EventRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val apiService = RetrofitService()

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
    fun `when getEvents should call getEvents from service`() = runBlockingTest {
        coEvery { apiService.service.getEvents() } returns EVENT_LIST

        EventRepositoryImpl(apiService).getEvents()

        coVerify { apiService.service.getEvents() }
    }

}