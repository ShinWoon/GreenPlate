package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemHomeRecommendBinding
import com.ssafy.green_plate.dto.Product

class HomeRecommendAdapter(val context : Context, private var items : List<Product>) : RecyclerView.Adapter<HomeRecommendAdapter.HomeRecommendViewHolder>() {
    inner class HomeRecommendViewHolder(private val binding: ListItemHomeRecommendBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data: Product) {
                binding.recommendMenuTv.text = data.name
                binding.recommendIv.setImageResource(R.drawable.salad01)
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecommendAdapter.HomeRecommendViewHolder {
        val binding = ListItemHomeRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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