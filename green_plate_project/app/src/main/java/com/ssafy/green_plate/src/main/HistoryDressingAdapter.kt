package com.ssafy.green_plate.src.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartSubDressingBinding
import com.ssafy.green_plate.dto.AddedStuffInfo
import com.ssafy.green_plate.util.CommonUtils

class HistoryDressingAdapter(val context: Context, private var items: MutableList<AddedStuffInfo>) : RecyclerView.Adapter<HistoryDressingAdapter.HistoryDressingViewHolder>() {

    inner class HistoryDressingViewHolder(private val binding: ListItemShoppingcartSubDressingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddedStuffInfo) {
            // 자식 RecyclerView의 아이템 데이터를 바인딩
//            binding.subDressingNameTv.text = item.
//            Log.d(TAG, "bind: $item")
            binding.apply {
                subDressingNameTv.text = item.stuffName
                subDressingPriceTv.text = CommonUtils.makeComma(item.stuffPrice)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDressingViewHolder {
        val binding = ListItemShoppingcartSubDressingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryDressingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryDressingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}