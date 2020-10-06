package com.sicredi.sicredipostapp.ui.eventdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sicredi.sicredipostapp.R
import com.sicredi.sicredipostapp.ui.MessageIconDialog
import com.sicredi.sicredipostapp.ui.extension.*
import kotlinx.android.synthetic.main.event_detail_activity.*
import kotlinx.android.synthetic.main.event_detail_activity.tv_subtitle
import kotlinx.android.synthetic.main.event_detail_activity.tv_title
import kotlinx.android.synthetic.main.view_check_in_bottom_sheet.*
import kotlinx.android.synthetic.main.view_network_error.view.*
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
        setUpTryAgainLoadEvent()
        setUpErrorNetwork()
        setUpBottomSheet()
        setUpInputChange()
        setUpEventDetails()
        setUpCheckIn()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            onClickShareChooser()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setUpLoading() {
        viewModel.loadingDetailsLiveData.observe(this, Observer { isLoading ->
            view_loading_detail.visibleOrGone(isLoading)
        })
    }

    private fun setUpErrorNetwork() {
        viewModel.errorEventDetailLiveData.observe(this, Observer { hasError ->
            view_network_error_detail.visibleOrGone(hasError)
        })
    }

    private fun setUpTryAgainLoadEvent() {
        view_network_error_detail.btn_try_again.setOnClickListener {
            viewModel.getEvent(getEventId())
        }
    }

    private fun onClickShareChooser() {

        val shareText = viewModel.getShareText()
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setUpEventDetails() {
        viewModel.eventDetailLiveData.observe(this, Observer {
            content_detail.visible()
            it?.let { event ->
                iv_post_image.loadImage(event.image)
                tv_title.text = event.title
                tv_subtitle.text = event.description
                tv_date.text = event.date.toDateFormatted()
                tv_price.text = event.price.toMoneyFormat()
            }
            bottom_sheet_check_in.visible()
        })
        viewModel.getEvent(getEventId())
    }

    private fun setUpCheckIn() {

        viewModel.loadingCheckInLiveData.observe(this, Observer { isLoading ->
            if (isLoading) {
                btn_confirm_check_in.gone()
                progress_bar_btn_check_in.visible()
            } else {
                btn_confirm_check_in.visible()
                progress_bar_btn_check_in.gone()
            }
        })

        viewModel.checkInResultLiveData.observe(this, Observer {
            it?.let { success ->
                hideBottomSheet()

                if (success) showSuccessCheckInDialog() else showErrorCheckInDialog()
            }
        })
        btn_confirm_check_in.setOnClickListener { onClickConfirmCheckIn() }
    }

    private fun setUpInputChange() {
        txt_name.addTextChangedListener { validateUserName() }
        txt_email.addTextChangedListener { validateUserEmail() }
    }

    private fun onClickConfirmCheckIn() {
        val eventId = getEventId()
        val userName: String = txt_name.text.toString()
        val userEmail: String = txt_email.text.toString()

        validateUserName()
        validateUserEmail()

        if (viewModel.validateText(userName) && viewModel.validateEmail(userEmail))
            viewModel.postCheckIn(eventId, userName, userEmail)
    }

    private fun validateUserName() {
        val userName: String = txt_name.text.toString()
        til_name.error =
            if (viewModel.validateText(userName).not()) getString(R.string.txt_fill_name) else null
    }

    private fun validateUserEmail() {
        val userEmail: String = txt_email.text.toString()
        til_email.error = if (viewModel.validateEmail(userEmail).not())
            getString(R.string.txt_fill_email) else null
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

    private fun showErrorCheckInDialog() {
        MessageIconDialog
            .newInstance(
                getString(R.string.txt_ops_error),
                getString(R.string.txt_check_in_error),
                R.drawable.ic_done
            )
            .show(supportFragmentManager, MessageIconDialog.TAG)
    }

    private fun showSuccessCheckInDialog() {
        MessageIconDialog
            .newInstance(
                getString(R.string.txt_all_right),
                getString(R.string.txt_check_in_ok_description),
                R.drawable.ic_done
            )
            .show(supportFragmentManager, MessageIconDialog.TAG)
    }

    private fun getEventId() = intent.getStringExtra(EVENT_ID);
}