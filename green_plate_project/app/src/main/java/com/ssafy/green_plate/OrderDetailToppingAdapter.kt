package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderPageToppingBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.CommonUtils

class OrderDetailToppingAdapter (val context : Context, private var items : List<Product>) : RecyclerView.Adapter<OrderDetailToppingAdapter.OrderDetailToppingViewHolder> (){
    inner class OrderDetailToppingViewHolder(private val binding : ListItemOrderPageToppingBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : Product) {
                binding.toppingTv.text = data.name
                binding.toppingPriceTv.text = CommonUtils.makeComma(data.price)
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailToppingViewHolder {
        val binding = ListItemOrderPageToppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderDetailToppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailToppingViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}