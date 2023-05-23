package com.ssafy.green_plate.src.main.order

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.green_plate.databinding.ListItemOrderPageToppingBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.CommonUtils
import kotlin.math.log

private const val TAG = "OrderDetailToppingAdapt_싸피"
class OrderDetailToppingAdapter (val context : Context, private var items : List<Product>) : RecyclerView.Adapter<OrderDetailToppingAdapter.OrderDetailToppingViewHolder> (){
    // 체크된 체크박스 아이템을 담을 리스트
    private var addedStuff = mutableListOf<Product>();

    inner class OrderDetailToppingViewHolder(private val binding : ListItemOrderPageToppingBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bindInfo(data : Product) {
                binding.toppingTv.text = data.name
                binding.toppingPriceTv.text = CommonUtils.makeComma(data.price)

                // checkbox changed Listener
                binding.toppingCbox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) { // 체크가 됐다면 list에 추가
                        addedStuff.add(data)
                        Log.d(TAG, "bindInfo: $addedStuff")

                    } else {
                        addedStuff.remove(data)
                        Log.d(TAG, "bindInfo: $addedStuff")
                    }
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailToppingViewHolder {
        val binding = ListItemOrderPageToppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return OrderDetailToppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailToppingViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    // 체크된 상품 목록 반환
    fun getCheckedItems(): List<Product> {
        Log.d(TAG, "getCheckedItems: $addedStuff")
        return addedStuff.toList()
    }

}