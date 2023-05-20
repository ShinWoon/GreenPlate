package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ItemOrderHistoryBinding

class OrderHistoryAdapter (val context : Context, private var items : List<String>): RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>() {
    inner class OrderHistoryViewHolder(private val binding : ItemOrderHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(item: String) {
            binding.orderItemNameTv.text = item

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        val binding = ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}