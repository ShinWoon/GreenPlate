package com.ssafy.green_plate.src.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartSubToppingBinding
import com.ssafy.green_plate.dto.AddedStuffInfo
import com.ssafy.green_plate.util.CommonUtils

class HistoryToppingAdapter (val context: Context, private var items: MutableList<AddedStuffInfo>) : RecyclerView.Adapter<HistoryToppingAdapter.HistoryToppingViewHolder>() {

    inner class HistoryToppingViewHolder(private val binding: ListItemShoppingcartSubToppingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddedStuffInfo) {
            // 자식 RecyclerView의 아이템 데이터를 바인딩
//            binding.subDressingNameTv.text = item.
//            Log.d(TAG, "bind: $item")
            binding.apply {
                subToppingNameTv.text = item.stuffName
                subToppingPriceTv.text = CommonUtils.makeComma(item.stuffPrice)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryToppingViewHolder {
        val binding = ListItemShoppingcartSubToppingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryToppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryToppingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}