package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderPageDressingBinding
import com.ssafy.green_plate.dto.Product

class OrderDetailDressingAdapter(val context : Context, private var items : List<Product>) : RecyclerView.Adapter<OrderDetailDressingAdapter.OrderDetailDressingViewHolder>() {
    inner class OrderDetailDressingViewHolder(private val binding: ListItemOrderPageDressingBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : Product) {

            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailDressingViewHolder {
        val binding = ListItemOrderPageDressingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderDetailDressingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailDressingViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}