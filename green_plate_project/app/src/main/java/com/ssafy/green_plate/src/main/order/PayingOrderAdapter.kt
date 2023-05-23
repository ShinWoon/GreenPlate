package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ItemPayOrderRecyclerViewBinding
import com.ssafy.green_plate.dto.ShoppingCart
import com.ssafy.green_plate.util.CommonUtils

class PayingOrderAdapter(val context : Context, private var items : List<ShoppingCart>) : RecyclerView.Adapter<PayingOrderAdapter.PayingOrderViewHolder>() {
    inner class PayingOrderViewHolder(private val binding: ItemPayOrderRecyclerViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingCart) {
            binding.payingMenuNameTv.text = item.productName
            binding.payingMainDressingNameTv.text = item.dressingName
            var toppings = ""
            var totalPrice = 0
            for (topping in item.addedStuff) {
                toppings = toppings + topping.name + "/"
                totalPrice += topping.price
            }
            binding.payingMenuToppingTv.text = toppings.dropLast(1)
            binding.payingMenuPriceTv.text = CommonUtils.makeComma(item.productPrice + totalPrice)
            Glide.with(context)
                .load("${ApplicationClass.MENU_IMGS_URL}${item.productImg}")
                .into(binding.payingMenuImgIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayingOrderViewHolder {
        val binding = ItemPayOrderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayingOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PayingOrderViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}