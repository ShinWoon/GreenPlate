package com.ssafy.green_plate.src.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.TAG
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemHomeRecommendBinding
import com.ssafy.green_plate.dto.Product

class HomeRecommendAdapter(val context : Context, private var items : List<Product>) : RecyclerView.Adapter<HomeRecommendAdapter.HomeRecommendViewHolder>() {
    inner class HomeRecommendViewHolder(private val binding: ListItemHomeRecommendBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data: Product) {
                binding.recommendMenuTv.text = data.name
                Log.d(TAG, "bindInfo: ${data.name}")
                Glide.with(itemView)
                    .load("${ApplicationClass.MENU_IMGS_URL}${data.img}")
                    .into(binding.recommendIv)
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecommendAdapter.HomeRecommendViewHolder {
        val binding =
            ListItemHomeRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeRecommendAdapter.HomeRecommendViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}