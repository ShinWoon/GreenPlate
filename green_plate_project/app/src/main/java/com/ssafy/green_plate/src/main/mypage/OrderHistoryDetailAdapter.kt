package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderHistoryDetailBinding
import com.ssafy.green_plate.dto.AddedStuffInfo
import com.ssafy.green_plate.dto.OrderHistory
import com.ssafy.green_plate.src.main.HistoryDressingAdapter
import com.ssafy.green_plate.src.main.HistoryToppingAdapter
import com.ssafy.green_plate.src.main.SubToppingAdapter
import com.ssafy.green_plate.util.CommonUtils

class OrderHistoryDetailAdapter(val context: Context, private var items: MutableList<OrderHistory>): RecyclerView.Adapter<OrderHistoryDetailAdapter.OrderHistoryDetailViewHolder>() {

    inner class OrderHistoryDetailViewHolder(private val binding: ListItemOrderHistoryDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : OrderHistory) {
                binding.apply {
                    orderHistoryItemNameTv.text = data.productName
                    orderHistoryItemCntTv.text = "1"
                    orderHistoryItemPriceTv.text = CommonUtils.makeComma(data.productPrice)
                    orderHistoryMainDressingTv.text = if(data.dressingName.equals("")) "X" else data.dressingName

//                    orderHistorySubDressingRv.apply {
//                        adapter = HistoryDressingAdapter(context, data.addedStuff as MutableList<AddedStuffInfo>)
//                    }
                    orderHistorySubToppingRv.apply {
                        adapter = HistoryToppingAdapter(context,
                            data.addedStuff as MutableList<AddedStuffInfo>
                        )
                    }
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