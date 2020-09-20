package com.sicredi.sicredipostapp.ui.eventdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.ui.extension.gone
import com.sicredi.sicredipostapp.ui.extension.loadImage
import com.sicredi.sicredipostapp.ui.extension.visible
import kotlinx.android.synthetic.main.view_check_in_bottom_sheet.*
import kotlinx.android.synthetic.main.event_detail_activity.*
import kotlinx.android.synthetic.main.event_detail_activity.toolbar_event_detail
import kotlinx.android.synthetic.main.event_detail_activity.view_loading
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailActivity : AppCompatActivity() {

    private val viewModel: EventDetailViewModel by viewModel()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CardView>

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
        setUpLoading()
        setUpBottomSheet()
        setUpEventDetails()
        setUpCheckInResult()

        btn_confirm_checkin.setOnClickListener { onClickConfirmCheckIn() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            openShareChooser()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setUpLoading() {
        viewModel.loadingDetailsLiveData.observe(this, Observer { isLoading ->
            if (isLoading) view_loading.visible() else view_loading.gone()
        })
    }

    private fun openShareChooser() {
        //TODO: Ajust message share
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
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
        bottomSheetBehavior = BottomSheetBehavior.from(bs_check_in)
        bottomSheetBehavior.isHideable = false
        view_slide_up.setOnClickListener { handleCollapseBottomSheet() }
        tv_check_in.setOnClickListener { handleCollapseBottomSheet() }
    }

    private fun hideBottomSheet() {
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun handleCollapseBottomSheet() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        else
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
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