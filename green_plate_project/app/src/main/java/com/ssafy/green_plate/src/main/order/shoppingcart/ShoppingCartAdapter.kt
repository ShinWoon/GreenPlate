package com.ssafy.green_plate.src.main.order.shoppingcart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemShoppingcartBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.dto.ShoppingCart
import com.ssafy.green_plate.src.main.SubDressingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter
import com.ssafy.green_plate.util.CommonUtils

private const val TAG = "ShoppingCartAdapter_싸피"
class ShoppingCartAdapter(val context : Context, private var items : List<ShoppingCart>, private val listener: OnItemDeleteClickListener?) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    interface OnItemDeleteClickListener {
        fun onItemDeleteClick(position: Int)
    }
    inner class ShoppingCartViewHolder(private val binding: ListItemShoppingcartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.shoppingItemCancelBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemDeleteClick(position)
                }
            }
        }

        fun bind(item: ShoppingCart) {
            binding.shoppingItemNameTv.text = item.productName
            binding.shoppingItemPriceTv.text = CommonUtils.makeComma(item.productPrice)
            binding.shoppingItemCntTv.text = item.menuCnt.toString()
            Glide.with(context)
                .load("${ApplicationClass.MENU_IMGS_URL}${item.productImg}")
                .into(binding.shoppingItemImgIv)
            binding.subToppingRv.adapter = SubToppingAdapter(context, item.addedStuff)

            Log.d(TAG, "bind: $item")

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