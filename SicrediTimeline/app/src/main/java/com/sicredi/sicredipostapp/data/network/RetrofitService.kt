package com.sicredi.sicredipostapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {
    private fun initRetrofit(): Retrofit {
        //TODO: Passar URL para Constante na Config
        return Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: ApiService = initRetrofit().create(ApiService::class.java)
}