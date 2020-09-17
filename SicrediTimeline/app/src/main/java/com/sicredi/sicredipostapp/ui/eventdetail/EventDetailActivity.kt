package com.sicredi.sicredipostapp.ui.eventdetail

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.data.repository.EventRepositoryImpl
import com.sicredi.sicredipostapp.ui.extension.loadImage
import kotlinx.android.synthetic.main.check_in_bottom_sheet_fragment.*
import kotlinx.android.synthetic.main.event_detail_activity.*


class EventDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: EventDetailViewModel

    companion object {
        const val EVENT_ID = "idEvent"
        fun start(context: Context, eventId: String) {
            val intent =
                Intent(context, EventDetailActivity::class.java).apply {
                    putExtra(EVENT_ID, eventId)
                }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail_activity)

        setUpToolbar()
        setUpViewModel()
        setUpBottomSheet()

        setUpEventDetails()
        setUpCheckInResult()

        btn_confirm_checkin.setOnClickListener { onClickConfirmCheckIn() }
    }

    private fun setUpEventDetails() {
        viewModel.eventDetailLiveData.observe(this, Observer {
            it?.let { event ->
                iv_post_image.loadImage(event.image)
                tv_title.text = event.title
                tv_subtitle.text = event.description
            }
        })
        viewModel.getEvent(intent.getStringExtra(EVENT_ID))
    }

    private fun setUpCheckInResult() {
        viewModel.checkInResultLiveData.observe(this, Observer {
            it?.let { success ->
                hideBottomSheet()
                if (success) {
                    Toast.makeText(
                        this,
                        getString(R.string.txt_check_in_success),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, getString(R.string.txt_check_in_error), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun onClickConfirmCheckIn() {
        val eventId = intent.getStringExtra(EVENT_ID)
        val userName: String = txt_name.text.toString()
        val userEmail: String = txt_email.text.toString()

        if (userName.isEmpty())
            txt_name.error = getString(R.string.txt_fill_name)

        if (userEmail.isEmpty())
            txt_email.error = getString(R.string.txt_fill_email)

        if (userName.isNotEmpty() && userEmail.isNotEmpty())
            viewModel.postCheckIn(eventId, userName, userEmail)
    }

    private fun setUpBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(bs_check_in)
        bottomSheetBehavior.isHideable = false
    }

    private fun hideBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(bs_check_in)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun setUpViewModel() {
        viewModel = EventDetailViewModel.EventDetailViewModelFactory(EventRepositoryImpl())
            .create(EventDetailViewModel::class.java)
    }

    private fun setUpToolbar() {
        toolbar_event_detail.title = getString(R.string.activity_event_detail_title);
        setSupportActionBar(toolbar_event_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_event_detail.setNavigationOnClickListener {
            finish()
        }
    }
}