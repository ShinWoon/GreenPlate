package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemHomeRecentMenuBinding

class RecentMenuAdapter(val context : Context, private var items : List<String>): RecyclerView.Adapter<RecentMenuAdapter.RecentMenuViewHolder>() {
    inner class RecentMenuViewHolder(private val binding: ListItemHomeRecentMenuBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : String) {
                binding.recentRecommendMenuTv.text = data
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