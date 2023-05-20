package com.ssafy.green_plate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ItemOrderHistoryBinding

class OrderHistoryAdapter (val context : Context, private var items : List<String>): RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>() {


    inner class OrderHistoryViewHolder(private val binding : ItemOrderHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
        }
        fun bindInfo(item: String) {
            binding.orderItemNameTv.text = item

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        val binding = ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    // 클릭 이벤트 리스너
    private var clickListener: ((Int) -> Unit)? = null

    // 클릭 이벤트를 처리하기 위한 메서드
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        clickListener = listener
    }

    private fun onItemClick(position: Int) {
        clickListener?.invoke(position)
    }
}