package com.ssafy.green_plate

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartBinding
import kotlinx.coroutines.NonDisposableHandle.parent

private const val TAG = "ShoppingCartAdapter_싸피"
class ShoppingCartAdapter(val context : Context, private var items : List<String>) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {

    inner class ShoppingCartViewHolder(private val binding: ListItemShoppingcartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var subDressingAdapter: SubDressingAdapter = SubDressingAdapter()
        private var subToppingAdapter: SubToppingAdapter = SubToppingAdapter()

        init {
            binding.subDressingRv.adapter = subDressingAdapter
            binding.subToppingRv.adapter = subToppingAdapter

            var subItems = listOf("토핑1", "토핑2")

            subDressingAdapter.setItems(subItems)
            subDressingAdapter.notifyDataSetChanged()
            subToppingAdapter.setItems(subItems)
            subToppingAdapter.notifyDataSetChanged()
            Log.d(TAG, ": $subItems")
        }
        fun bind(item: String) {
            binding.shoppingItemNameTv.text = item
            Log.d(TAG, "bind: $item")


//            Log.d(TAG, "bind: $subItems")
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartAdapter.ShoppingCartViewHolder {
        val binding =
            ListItemShoppingcartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingCartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ShoppingCartAdapter.ShoppingCartViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        Log.d(TAG, "onBindViewHolder: ")
    }

    override fun getItemCount(): Int {
        return items.size
    }
}