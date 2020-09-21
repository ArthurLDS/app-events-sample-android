package com.sicredi.sicredipostapp.testUtil

import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel

object TestData {

    const val EMPTY_TEXT = ""
    const val BLANK_TEXT = "  "
    const val CONTENT_TEXT = "Testandoo..."
    const val VALID_EMAIL = "teste@teste.com"
    const val INVALID_EMAIL = "teste@teste"
    const val IS_LOADING = true
    const val NOT_LOADING = false
    const val HAS_SUCCESS = true
    const val HAS_ERROR = false
    val CHECK_IN = CheckInModel(
        "1",
        "Teste da Silva",
        "test@testando.com"
    )
    val EVENT_DETAIL = EventModel(
        id = "1",
        title = "Evento 1",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
        longitude = "3423434",
        latitude = "324234234",
        image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg",
        date = 1534784400000,
        price = 29.90f
    )
    val EVENT_LIST = listOf(
        EVENT_DETAIL,
        EVENT_DETAIL,
        EVENT_DETAIL
    )

}