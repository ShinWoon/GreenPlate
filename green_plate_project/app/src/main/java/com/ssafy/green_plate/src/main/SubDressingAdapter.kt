package com.ssafy.green_plate.src.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemShoppingcartSubDressingBinding

class SubDressingAdapter(val context : Context, private var items : List<String>) : RecyclerView.Adapter<SubDressingAdapter.SubDressingViewHolder>() {

    inner class SubDressingViewHolder(private val binding: ListItemShoppingcartSubDressingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : String) {
            // 자식 RecyclerView의 아이템 데이터를 바인딩
            binding.subDressingNameTv.text = item
            Log.d(com.ssafy.green_plate.TAG, "bind: $item")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubDressingViewHolder {
        val binding = ListItemShoppingcartSubDressingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubDressingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubDressingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        Log.d(com.ssafy.green_plate.TAG, "onBindViewHolder: $items")
    }

    override fun getItemCount(): Int {
        return items.size
    }


}