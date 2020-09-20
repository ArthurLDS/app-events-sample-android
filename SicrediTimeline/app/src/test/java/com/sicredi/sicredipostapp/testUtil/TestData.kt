package com.sicredi.sicredipostapp.testUtil

import com.sicredi.sicredipostapp.data.model.CheckInModel
import com.sicredi.sicredipostapp.data.model.EventModel

object TestData {

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
        image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"
    )
    val EVENT_LIST = listOf(
        EventModel(
            id = "1",
            title = "Evento 1",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
            longitude = "3423434",
            latitude = "324234234",
            image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"
        ),
        EventModel(
            id = "2",
            title = "Evento 2",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
            longitude = "3423434",
            latitude = "234234234",
            image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"
        ),
        EventModel(
            id = "3",
            title = "Evento 3",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique bibendum sodales. Etiam mattis, dolor lacinia pharetra dapibus, justo tellus aliquam mauris, pharetra venenatis nisi nibh sit amet massa. Duis fringilla molestie venenatis. Ut semper lacus libero.",
            longitude = "3423434",
            latitude = "sdf234234",
            image = "https://blog.sobraci.com.br/wp-content/uploads/2019/11/original-eee528b829e9cac6449f70bfa64fca5d-780x450.jpg"
        )
    )

}