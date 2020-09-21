package com.sicredi.sicredipostapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sicredi.sicredipostapp.data.model.EventModel
import com.sicredi.sicredipostapp.data.repository.EventRepository
import com.sicredi.sicredipostapp.testUtil.TestData.BLANK_TEXT
import com.sicredi.sicredipostapp.testUtil.TestData.CHECK_IN
import com.sicredi.sicredipostapp.testUtil.TestData.CONTENT_TEXT
import com.sicredi.sicredipostapp.testUtil.TestData.EMPTY_TEXT
import com.sicredi.sicredipostapp.testUtil.TestData.EVENT_DETAIL
import com.sicredi.sicredipostapp.testUtil.TestData.HAS_ERROR
import com.sicredi.sicredipostapp.testUtil.TestData.HAS_SUCCESS
import com.sicredi.sicredipostapp.testUtil.TestData.INVALID_EMAIL
import com.sicredi.sicredipostapp.testUtil.TestData.IS_LOADING
import com.sicredi.sicredipostapp.testUtil.TestData.NOT_LOADING
import com.sicredi.sicredipostapp.testUtil.TestData.VALID_EMAIL
import com.sicredi.sicredipostapp.ui.eventdetail.EventDetailViewModel
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EventDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<EventRepository>()

    private val eventDetailLiveDataObserver = mockk<Observer<EventModel>>(relaxed = true)
    private val checkInResultLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val loadingDetailsLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val loadingCheckInLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

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
    fun `when get event detail with success and set event detail live data`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.getEvent(EVENT_DETAIL.id) } returns EVENT_DETAIL

        viewModel.getEvent(EVENT_DETAIL.id)

        coVerifyOrder {
            loadingDetailsLiveDataObserver.onChanged(IS_LOADING)
            repository.getEvent(EVENT_DETAIL.id)
            eventDetailLiveDataObserver.onChanged(EVENT_DETAIL)
            loadingDetailsLiveDataObserver.onChanged(NOT_LOADING)
        }
        confirmVerified(loadingDetailsLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(eventDetailLiveDataObserver)
    }

    @Test
    fun `when get event detail with error and not set event detail live data`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.getEvent(EVENT_DETAIL.id) } throws Exception()

        viewModel.getEvent(EVENT_DETAIL.id)

        coVerifyOrder {
            loadingDetailsLiveDataObserver.onChanged(IS_LOADING)
            repository.getEvent(EVENT_DETAIL.id)
            loadingDetailsLiveDataObserver.onChanged(NOT_LOADING)
        }

        confirmVerified(loadingDetailsLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(eventDetailLiveDataObserver)
    }

    @Test
    fun `when post check in with success and set check in result live data `() {
        val viewModel = instantiateViewModel()
        coEvery { repository.postCheckIn(CHECK_IN) } returns mockk()

        viewModel.postCheckIn(CHECK_IN.eventId, CHECK_IN.name, CHECK_IN.email)

        coVerifyOrder {
            loadingCheckInLiveDataObserver.onChanged(IS_LOADING)
            repository.postCheckIn(CHECK_IN)
            checkInResultLiveDataObserver.onChanged(HAS_SUCCESS)
            loadingCheckInLiveDataObserver.onChanged(NOT_LOADING)
        }
        confirmVerified(loadingCheckInLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(checkInResultLiveDataObserver)
    }

    @Test
    fun `when post check in with error and not set check in result live data`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.postCheckIn(CHECK_IN) } throws Exception()

        viewModel.postCheckIn(CHECK_IN.eventId, CHECK_IN.name, CHECK_IN.email)

        coVerifyOrder {
            loadingCheckInLiveDataObserver.onChanged(IS_LOADING)
            repository.postCheckIn(CHECK_IN)
            checkInResultLiveDataObserver.onChanged(HAS_ERROR)
            loadingCheckInLiveDataObserver.onChanged(NOT_LOADING)
        }
        confirmVerified(loadingCheckInLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(checkInResultLiveDataObserver)
    }

    @Test
    fun `when getShareText then get the description of event loaded`() {
        val viewModel = instantiateViewModel()
        coEvery { repository.getEvent(EVENT_DETAIL.id) } returns EVENT_DETAIL

        viewModel.getEvent(EVENT_DETAIL.id)

        val descriptionCorrect = "${EVENT_DETAIL?.title}\n\n${EVENT_DETAIL.description}"
        assertEquals(descriptionCorrect, viewModel.getShareText())
    }

    @Test
    fun `when validate text empty then return false`() {
        val viewModel = instantiateViewModel()

        assertFalse(viewModel.validateText(EMPTY_TEXT))
    }

    @Test
    fun `when validate text blank then return false`() {
        val viewModel = instantiateViewModel()

        assertFalse(viewModel.validateText(BLANK_TEXT))
    }

    @Test
    fun `when validate text not with content then return true`() {
        val viewModel = instantiateViewModel()

        assertTrue(viewModel.validateText(CONTENT_TEXT))
    }

    @Test
    fun `when validate invalid email then return false`() {
        val viewModel = instantiateViewModel()

        assertTrue(viewModel.validateText(INVALID_EMAIL))
    }

    @Test
    fun `when validate valid email then return true`() {
        val viewModel = instantiateViewModel()

        assertTrue(viewModel.validateEmail(VALID_EMAIL))
    }

    private fun instantiateViewModel(): EventDetailViewModel {
        val viewModel = EventDetailViewModel(repository)
        viewModel.eventDetailLiveData.observeForever(eventDetailLiveDataObserver)
        viewModel.checkInResultLiveData.observeForever(checkInResultLiveDataObserver)
        viewModel.loadingDetailsLiveData.observeForever(loadingDetailsLiveDataObserver)
        viewModel.loadingCheckInLiveData.observeForever(loadingCheckInLiveDataObserver)
        return viewModel
    }


}