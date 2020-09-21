package com.sicredi.sicredipostapp.data

import com.sicredi.sicredipostapp.data.network.RetrofitService
import com.sicredi.sicredipostapp.data.repository.EventRepositoryImpl
import com.sicredi.sicredipostapp.testUtil.TestData.CHECK_IN
import com.sicredi.sicredipostapp.testUtil.TestData.EVENT_DETAIL
import com.sicredi.sicredipostapp.testUtil.TestData.EVENT_LIST
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
    private val apiService: RetrofitService = mockk()

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

    @Test
    fun `when getEvent should call getEvent from service`() = runBlockingTest {
        coEvery { apiService.service.getEvent(EVENT_DETAIL.id) } returns EVENT_DETAIL

        EventRepositoryImpl(apiService).getEvent(EVENT_DETAIL.id)

        coVerify { apiService.service.getEvent(EVENT_DETAIL.id) }
    }

    @Test
    fun `when postCheckIn should call postCheckIn from service`() = runBlockingTest {
        coEvery { apiService.service.postCheckIn(CHECK_IN) } returns mockk()

        EventRepositoryImpl(apiService).postCheckIn(CHECK_IN)

        coVerify { apiService.service.postCheckIn(CHECK_IN) }
    }

}