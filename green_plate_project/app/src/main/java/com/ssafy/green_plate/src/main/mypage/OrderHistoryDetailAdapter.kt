package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderHistoryDetailBinding
import com.ssafy.green_plate.models.OrderDetailResponse

class OrderHistoryDetailAdapter(val context: Context, private var items: MutableList<OrderDetailResponse>): RecyclerView.Adapter<OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder>() {

    inner class OrderHistoryDetailViewHolder(private val binding: ListItemOrderHistoryDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : OrderDetailResponse) {
//                binding.orderHistoryMainDressingTv.text = data.
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder {
        val binding = ListItemOrderHistoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHistoryDetailViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}