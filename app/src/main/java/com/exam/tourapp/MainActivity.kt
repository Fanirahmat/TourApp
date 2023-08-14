package com.exam.tourapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.tourapp.adapters.WisataItemAdapter
import com.exam.tourapp.api.ApiConfig
import com.exam.tourapp.models.ResponseListTempatWisata
import com.exam.tourapp.models.WisataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText

    private lateinit var rvWisata: RecyclerView
    private lateinit var adapter: WisataItemAdapter

    private var listWisataOrigin = arrayListOf<WisataItem>()
    private var listWisataFiltered = arrayListOf<WisataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById<EditText>(R.id.et_search)
        rvWisata = findViewById<RecyclerView>(R.id.rv_wisata)

        adapter = WisataItemAdapter(listWisataFiltered)
        rvWisata.adapter = adapter
        rvWisata.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL , false)

        getListWisata()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }
        })

    }

    private fun filterList(query: String) {
        listWisataFiltered.clear()

        listWisataOrigin.forEach { item ->
            if (query != "") {
                val nama = item.nama!!.lowercase(Locale.ROOT)
                if (nama.contains(query.lowercase(Locale.ROOT))) {
                    listWisataFiltered.add(item)
                }
            } else {
                listWisataFiltered.add(item)
            }

        }

        adapter.notifyDataSetChanged()


    }


    fun getListWisata() {
        ApiConfig.getService().getListWisata().enqueue(object : Callback<ResponseListTempatWisata> {
            override fun onResponse(call: Call<ResponseListTempatWisata>, response: Response<ResponseListTempatWisata>) {
                if (response.isSuccessful) {
                    val data = response.body()

                    listWisataFiltered.clear()
                    listWisataOrigin.clear()

                    for (a in data?.wisata!!) {
                        a?.let { listWisataFiltered.add(it) }
                        a?.let { listWisataOrigin.add(it) }
                    }

                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<ResponseListTempatWisata>, t: Throwable) {

            }

        })
    }


}