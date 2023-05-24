package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderHistoryDetailBinding
import com.ssafy.green_plate.models.OrderDetailResponse
import com.ssafy.green_plate.util.CommonUtils

class OrderHistoryDetailAdapter(val context: Context, private var items: MutableList<OrderDetailResponse>): RecyclerView.Adapter<OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder>() {

    inner class OrderHistoryDetailViewHolder(private val binding: ListItemOrderHistoryDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : OrderDetailResponse) {
                binding.apply {
                    orderHistoryItemNameTv.text = data.productName
                    orderHistoryItemCntTv.text = data.quantity.toString()
                    orderHistoryItemPriceTv.text = CommonUtils.makeComma(data.unitPrice)
                    orderHistoryMainDressingTv.text = if(dressingInfo.get(data.dressingId).equals("")) "X" else dressingInfo.get(data.dressingId)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder {
        val binding = ListItemOrderHistoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHistoryDetailViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    companion object {
        private val dressingInfo = mapOf(31 to "시저 드레싱", 32 to "오리엔탈 드레싱", 33 to "발사믹 드레싱", 34 to "레몬 드레싱", 35 to "머스타드 드레싱", 36 to "칠리 드레싱", 37 to "")
    }
}