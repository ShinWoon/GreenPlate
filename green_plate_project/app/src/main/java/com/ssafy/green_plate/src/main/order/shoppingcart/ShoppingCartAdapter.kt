package com.ssafy.green_plate.src.main.order.shoppingcart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartBinding
import com.ssafy.green_plate.src.main.SubDressingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter

class ShoppingCartAdapter(val context : Context, private var items : List<String>) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    inner class ShoppingCartViewHolder(private val binding: ListItemShoppingcartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            var subItems = listOf("토핑1", "토핑2")
            binding.subDressingRv.adapter = SubDressingAdapter(context, subItems)
            binding.subToppingRv.adapter = SubToppingAdapter(context, subItems)

            Log.d(com.ssafy.green_plate.TAG, ": $subItems")
        }
        fun bind(item: String) {
            binding.shoppingItemNameTv.text = item
            Log.d(com.ssafy.green_plate.TAG, "bind: $item")


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val binding =
            ListItemShoppingcartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingCartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}