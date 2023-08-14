package com.exam.tourapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exam.tourapp.DetailWisataActivity
import com.exam.tourapp.R
import com.exam.tourapp.api.ApiConfig
import com.exam.tourapp.models.WisataItem

class WisataItemAdapter(
    var list: List<WisataItem>
) : RecyclerView.Adapter<WisataItemAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvNamaWisata: TextView
        var ivWisata: ImageView
        init {
            tvNamaWisata = view.findViewById(R.id.tv_nama_wisata)
            ivWisata = view.findViewById(R.id.iv_wisata)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WisataItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_list_wisata, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WisataItemAdapter.ViewHolder, position: Int) {
        val item = list[position]

        holder.tvNamaWisata.text = item.nama

        //var urlImage = item.gambarUrl
        val urlImage = item.gambarUrl?.split("/api/")

        if (urlImage != null) {
            if (urlImage.size > 1) {
                Glide.with(holder.ivWisata)
                    .load(ApiConfig.baseUrl + urlImage[1])
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.ivWisata)
            }
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailWisataActivity::class.java)
            intent.putExtra("id", item.id.toString())
            context.startActivity(intent)
        }


    }





}