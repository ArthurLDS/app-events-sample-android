package com.sicredi.sicredipostapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sicredi.sicredipostapp.R
import kotlinx.android.synthetic.main.view_success_check_in.*

class MessageIconDialog : DialogFragment() {

    companion object {
        const val TAG = "MessageIconDialog"
        const val TITLE_DIALOG = "title"
        const val SUBTITLE_DIALOG = "subtitle"
        const val ICON_DIALOG = "icon"

        fun newInstance(title: String, subtitle: String, icon: Int): MessageIconDialog {

            val args = Bundle()
            args.putString(TITLE_DIALOG, title)
            args.putString(SUBTITLE_DIALOG, subtitle)
            args.putInt(ICON_DIALOG, icon)

            val fragment = MessageIconDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_success_check_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageIcon = arguments?.getInt(ICON_DIALOG)
        val title = arguments?.getString(TITLE_DIALOG)
        val subtitle = arguments?.getString(SUBTITLE_DIALOG)

        imageIcon?.let { iv_icon_dialog.setImageResource(it) }
        title?.let { tv_title_dialog.text = it }
        subtitle?.let { tv_subtitle_dialog.text = it }
        btn_confirm_message_ok_dialog.setOnClickListener { this.dismiss() }
    }

}