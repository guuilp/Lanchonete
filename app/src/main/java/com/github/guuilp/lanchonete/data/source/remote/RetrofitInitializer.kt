package com.github.guuilp.lanchonete.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.31.185:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun lanchoneteService() = retrofit.create(LanchoneteService::class.java)
}