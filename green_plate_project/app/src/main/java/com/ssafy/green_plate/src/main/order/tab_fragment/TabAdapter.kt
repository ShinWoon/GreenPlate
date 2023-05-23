package com.ssafy.green_plate.src.main.order.tab_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemOrderPageBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.util.CommonUtils

class TabAdapter (val context : Context, private var items : List<Product>) : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {
    inner class TabViewHolder(private val binding: ListItemOrderPageBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : Product) {
                binding.orderPageMenuNameTv.text = data.name
                binding.orderPageMenuEngNameTv.text = data.englishName
                binding.orderPageMenuPriceTv.text = CommonUtils.makeComma(data.price)
                Glide.with(itemView)
                    .load("${ApplicationClass.MENU_IMGS_URL}${data.img}")
                    .into(binding.orderPageMenuIv)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val binding = ListItemOrderPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TabViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bindInfo(item)
            itemView.setOnClickListener {
                itemClickListner.onClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ItemClickListener {
        fun onClick(data : Product)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}