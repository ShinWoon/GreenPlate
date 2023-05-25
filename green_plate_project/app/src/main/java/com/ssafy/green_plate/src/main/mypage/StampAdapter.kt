package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.R
import com.ssafy.green_plate.databinding.ItemStampRecyclerViewBinding

private const val TAG = "StampAdapter_싸피"
class StampAdapter (val context : Context, private var items : List<Boolean>) : RecyclerView.Adapter<StampAdapter.StampViewHolder>(){

    inner class StampViewHolder(private val binding: ItemStampRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(stamp : Boolean) {
            if(stamp) {
                // true 상태 -> stamp 찍어 주기
                binding.stampCircleView.background = ContextCompat.getDrawable(context,
                    R.drawable.stamp_circle
                )
                binding.stampLogoView.background = ContextCompat.getDrawable(context,
                    R.drawable.logo_color
                )
            } else {
                // false -> stamp 안 찍힌 상태
                binding.stampCircleView.background = ContextCompat.getDrawable(context,
                    R.drawable.no_stamp_circle
                )
                binding.stampLogoView.background = ContextCompat.getDrawable(context,
                    R.drawable.logo_gray
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StampViewHolder {
        val binding = ItemStampRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StampViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StampViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }



}