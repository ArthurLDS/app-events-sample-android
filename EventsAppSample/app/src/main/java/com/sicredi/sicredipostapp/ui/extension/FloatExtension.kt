package com.sicredi.sicredipostapp.ui.extension

import java.text.NumberFormat

fun Float.toMoneyFormat(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    return format.format(this)
}