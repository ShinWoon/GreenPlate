package com.ssafy.green_plate.src.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.green_plate.config.ApplicationClass
import com.ssafy.green_plate.databinding.ListItemHomeRecentMenuBinding
import com.ssafy.green_plate.dto.Product
import com.ssafy.green_plate.models.MenuDetailWithProductInfo

class RecentMenuAdapter(val context : Context, private var items : List<MenuDetailWithProductInfo>): RecyclerView.Adapter<RecentMenuAdapter.RecentMenuViewHolder>() {
    inner class RecentMenuViewHolder(private val binding: ListItemHomeRecentMenuBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bindInfo(data : MenuDetailWithProductInfo) {
                binding.recentRecommendMenuTv.text = data.productName
                Glide.with(itemView)
                    .load("${ApplicationClass.MENU_IMGS_URL}${data.productImg}")
                    .into(binding.recentRecommendIv)
                itemView.setOnClickListener{
                    itemClickListner.onClick(it, layoutPosition, data)
                }
            }
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentMenuViewHolder {
        val binding = ListItemHomeRecentMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentMenuViewHolder, position: Int) {
        val item = items[position]
        holder.bindInfo(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    interface ItemClickListener {
        fun onClick(view: View, position: Int, menu: MenuDetailWithProductInfo)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}