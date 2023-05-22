package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemHomeRecentMenuBinding
import com.ssafy.green_plate.models.MenuDetailWithProductInfo

class RecentMenuAdapter(val context : Context, private var items : List<MenuDetailWithProductInfo>): RecyclerView.Adapter<RecentMenuAdapter.RecentMenuViewHolder>() {
    inner class RecentMenuViewHolder(private val binding: ListItemHomeRecentMenuBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : MenuDetailWithProductInfo) {
                binding.recentRecommendMenuTv.text = data.productName
                Glide.with(itemView)
                    .load("${ApplicationClass.MENU_IMGS_URL}${data.productImg}")
                    .into(binding.recentRecommendIv)
            }
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentMenuAdapter.RecentMenuViewHolder {
        val binding = ListItemHomeRecentMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentMenuAdapter.RecentMenuViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}