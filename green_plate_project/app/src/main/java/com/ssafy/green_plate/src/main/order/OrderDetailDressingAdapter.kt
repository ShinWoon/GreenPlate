package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderPageDressingBinding
import com.ssafy.green_plate.dto.Product

class OrderDetailDressingAdapter(val context : Context, private var items : List<Product>) : RecyclerView.Adapter<OrderDetailDressingAdapter.OrderDetailDressingViewHolder>() {
    inner class OrderDetailDressingViewHolder(private val binding: ListItemOrderPageDressingBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val radioButton : RadioButton = binding.dressingRbtn
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

        holder.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}