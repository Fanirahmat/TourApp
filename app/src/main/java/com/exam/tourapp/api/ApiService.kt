package com.exam.tourapp.api

import com.exam.tourapp.models.ResponseDetailTempatWisata
import com.exam.tourapp.models.ResponseListTempatWisata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("purwakarta/wisata")
    fun getListWisata(): Call<ResponseListTempatWisata>

    @GET("purwakarta/wisata/{id}")
    fun getWisataDetail(
        @Path("id") id: String
    ): Call<ResponseDetailTempatWisata>
}