package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ItemCouponRecyclerViewBinding
import com.ssafy.green_plate.dto.Coupon

class CouponAdapter (val context : Context, private var items : List<Coupon>) : RecyclerView.Adapter<CouponAdapter.CouponViewHolder>() {
    inner class CouponViewHolder(private val binding: ItemCouponRecyclerViewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : Coupon) {
                binding.couponInfoTv.text = "[${data.couponType}] ${data.discountAmount}원 할인 쿠폰"
            }
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CouponAdapter.CouponViewHolder{
        val binding = ItemCouponRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CouponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CouponAdapter.CouponViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}