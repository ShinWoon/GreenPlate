package com.ssafy.green_plate.src.main.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.R
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ItemOrderHistoryBinding
import com.ssafy.green_plate.dto.OrderHistory
import com.ssafy.green_plate.models.OrderDetailResponse
import com.ssafy.green_plate.util.CommonUtils

private const val TAG = "OrderHistoryAdapter_싸피"
class OrderHistoryAdapter(val context: Context, private var items: MutableList<List<OrderDetailResponse>>): RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>() {


    inner class OrderHistoryViewHolder(private val binding : ItemOrderHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
        }
        fun bindInfo(items: List<OrderDetailResponse>) {
            binding.orderItemNameTv.text = if(items.size == 1) items[0].productName else "${items[0].productName} 외 ${items.size-1}개"
            binding.orderItemPriceTv.text = CommonUtils.makeComma(items[0].totalOrderPrice)
            binding.orderItemDateTv.text = CommonUtils.getFormattedString(items[0].orderDate)
            binding.orderStoreNameTv.text = items[0].storeName
            Log.d(TAG, "bindInfo: ${items[0].orderCompleted}")
            if (items[0].orderCompleted == 'N') {
                // blink 애니메이션
                val anim = AnimationUtils.loadAnimation(context, R.anim.blink_animation)
                binding.pickupWaitCircle.startAnimation(anim)
                binding.pickupWaitTv.startAnimation(anim)
                binding.pickupWaitCircle.setBackgroundResource(R.drawable.order_state_circle)
                binding.pickupWaitTv.setTextColor(ContextCompat.getColor(context, R.color.green_plate_state_green))
            } else {
                binding.pickupWaitCircle.setBackgroundResource(R.drawable.circle_1)
                binding.pickupWaitTv.setTextColor(ContextCompat.getColor(context, R.color.green_plate_progress_gray))
                binding.pickupCompleteCircle.setBackgroundResource(R.drawable.order_state_circle)
                binding.pickupCompleteTv.setTextColor(ContextCompat.getColor(context, R.color.green_plate_state_green))
            }

            Glide.with(itemView)
                .load("${ApplicationClass.MENU_IMGS_URL}${items[0].img}")
                .into(binding.orderItemImgIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        val binding =
            ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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