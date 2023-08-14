package com.exam.tourapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    const val baseUrl = "https://dev.farizdotid.com/api/"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}