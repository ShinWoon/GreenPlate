package com.ssafy.green_plate

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartSubToppingBinding

private const val TAG = "SubToppingAdapter_싸피"
class SubToppingAdapter(val context : Context, private var items : List<String>) : RecyclerView.Adapter<SubToppingAdapter.SubToppingViewHolder>() {
    inner class SubToppingViewHolder(private val binding: ListItemShoppingcartSubToppingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            // 자식 RecyclerView의 아이템 데이터를 바인딩
            binding.subToppingNameTv.text = item
            Log.d(TAG, "bind: $item")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubToppingAdapter.SubToppingViewHolder {
        val binding = ListItemShoppingcartSubToppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubToppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubToppingAdapter.SubToppingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return items.size
    }

}