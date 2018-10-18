package com.alextroy.habrss.api

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object HabrApp {
    private const val BASE_URL = "https://habr.com/"

    fun create(): HabrApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        return retrofit.create(HabrApi::class.java)
    }
}