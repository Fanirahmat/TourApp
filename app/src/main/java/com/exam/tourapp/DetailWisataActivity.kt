package com.exam.tourapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.exam.tourapp.api.ApiConfig
import com.exam.tourapp.models.ResponseDetailTempatWisata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class DetailWisataActivity : AppCompatActivity() {

    private lateinit var ivWisata: ImageView
    private lateinit var tvNamaWisata: TextView
    private lateinit var tvKategori: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var tvTiketWeekdays: TextView
    private lateinit var tvTiketWeekend: TextView
    private lateinit var btnLokasi: Button

    private var nama = ""
    private var latitude = ""
    private var longitude = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ivWisata = findViewById(R.id.iv_detail_wisata)
        tvNamaWisata = findViewById(R.id.tv_detail_nama_wisata)
        tvKategori = findViewById(R.id.tv_detail_kategori)
        tvDeskripsi = findViewById(R.id.tv_detail_deskripsi)
        tvTiketWeekdays = findViewById(R.id.tv_detail_tiket_weekdays)
        tvTiketWeekend = findViewById(R.id.tv_detail_tiket_weekend)
        btnLokasi = findViewById(R.id.btn_lihat_lokasi)

        val intent = intent
        val idWisata = intent.getStringExtra("id")
        idWisata?.let { getDetailWisata(it) }


        btnLokasi.setOnClickListener {
            val geoUri = "http://maps.google.com/maps?q=loc:$latitude,$longitude ($nama)"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
        }
    }

    fun getDetailWisata(id: String) {
        ApiConfig.getService().getWisataDetail(id).enqueue(object : Callback<ResponseDetailTempatWisata> {
            override fun onResponse(call: Call<ResponseDetailTempatWisata>, response: Response<ResponseDetailTempatWisata>) {
                if (response.isSuccessful) {
                    val data = response.body()

                    latitude = data?.latitude.toString()
                    longitude = data?.longitude.toString()
                    nama = data?.nama.toString()

                    tvNamaWisata.text = data?.nama
                    tvKategori.text = data?.kategori
                    tvDeskripsi.text = data?.deskripsi


                    val urlImage = data?.gambarUrl?.split("/api/")

                    if (urlImage != null) {
                        if (urlImage.size > 1) {
                            Glide.with(ivWisata)
                                .load(ApiConfig.baseUrl + urlImage[1])
                                .error(R.drawable.ic_launcher_background)
                                .into(ivWisata)
                        }
                    }


                }
            }

            override fun onFailure(call: Call<ResponseDetailTempatWisata>, t: Throwable) {

            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Simulate the back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}