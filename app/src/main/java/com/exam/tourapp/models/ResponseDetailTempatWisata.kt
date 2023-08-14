package com.exam.tourapp.models

import com.google.gson.annotations.SerializedName

data class ResponseDetailTempatWisata(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("gambar_url")
	val gambarUrl: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("photo_by")
	val photoBy: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
