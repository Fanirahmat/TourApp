package com.exam.tourapp.models

import com.google.gson.annotations.SerializedName

data class ResponseListTempatWisata(

	@field:SerializedName("wisata")
	val wisata: List<WisataItem?>? = null
)

data class WisataItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("gambar_url")
	val gambarUrl: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
